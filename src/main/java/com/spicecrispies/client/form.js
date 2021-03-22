// Submit button click event
submit = document.getElementById("submit");
submit.addEventListener('click', function(event) {
    const isrc = document.getElementById("isrc").value;
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;
    const releaseYear = document.getElementById("releaseYear").value;
    const artistFirstName = document.getElementById("artistFirstName").value;
    const artistLastName = document.getElementById("artistLastName").value;
});

// Dropdown menu on change event
dropdown = document.getElementById("actionSelect");
dropdown.addEventListener('change', function() {
    if (dropdown.value == 1 || dropdown.value == 4) {
        document.getElementById("title").disabled = true;
        document.getElementById("description").disabled = true;
        document.getElementById("releaseYear").disabled = true;
        document.getElementById("artistFirstName").disabled = true;
        document.getElementById("artistLastName").disabled = true;
    }
    else {
        document.getElementById("title").disabled = false
        document.getElementById("description").disabled = false;
        document.getElementById("releaseYear").disabled = false;
        document.getElementById("artistFirstName").disabled = false;
        document.getElementById("artistLastName").disabled = false;
    }
});