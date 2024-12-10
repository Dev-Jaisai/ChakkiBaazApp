let cart = {};

function addToCart(product, btn) {
    const quantity = parseInt(btn.previousElementSibling.value);
    cart[product] = (cart[product] || 0) + quantity;
    updateCart();
}

function updateCart() {
    const cartDiv = document.getElementById('cart-items');
    let total = 0;
    cartDiv.innerHTML = '';

    for (let [product, quantity] of Object.entries(cart)) {
        cartDiv.innerHTML += `
            <div class="cart-item">
                <span>${product}</span>
                <span>${quantity}kg × ₹10 = ₹${quantity * 10}</span>
            </div>`;
        total += quantity * 10;
    }

    document.getElementById('total').textContent = total;
}

function placeOrder() {
    if (Object.keys(cart).length === 0) {
        showOrderStatus('Please add items to cart before placing order', 'error');
        return;
    }

    const orderData = {
        items: cart,
        total: parseFloat(document.getElementById('total').textContent)
    };

    fetch('/shop/place-order', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 'success') {
            showOrderStatus(data.message, 'success');
            cart = {};
            updateCart();
        } else {
            showOrderStatus(data.message, 'error');
        }
    })
    .catch(error => {
        showOrderStatus('Error placing order. Please try again.', 'error');
    });
}

function showOrderStatus(message, type) {
    const statusDiv = document.getElementById('orderStatus');
    statusDiv.textContent = message;
    statusDiv.className = type;
    statusDiv.style.display = 'block';

    setTimeout(() => {
        statusDiv.style.display = 'none';
    }, 5000);
}