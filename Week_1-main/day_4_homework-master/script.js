function toggleVisibility() {
    // Get references to the entry and container divs
    var entryDiv = document.getElementById('entryDiv');
    var containerDiv = document.getElementById('containerDiv');

    // Toggle the visibility by changing the display property
    entryDiv.style.display = 'none';
    containerDiv.style.display = 'block';
}