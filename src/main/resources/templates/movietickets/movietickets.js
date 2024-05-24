document.querySelectorAll('.seat').forEach(seat => {
    seat.addEventListener('click', () => {
        seat.classList.toggle('selected');
    });
});

document.getElementById('confirmBtn').addEventListener('click', () => {
    const selectedSeats = document.querySelectorAll('.seat.selected');
    alert('You have selected ' + selectedSeats.length + ' seats.');
});
