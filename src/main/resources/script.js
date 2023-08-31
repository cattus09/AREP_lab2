document.addEventListener('DOMContentLoaded', function() {
    const folderForm = document.getElementById('folder-form');
    folderForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const folderPath = document.getElementById('folder-path').value;
        redirectToFolder(folderPath);
    });
});

function redirectToFolder(folderPath) {
    fetch(`=${folderPath}`)
        .then(response => {
            if (response.ok) {
                window.location.href = response.url;
            } else {
                console.error('Error redirecting to folder:', response.statusText);
            }
        })
        .catch(error => console.error('Error redirecting to folder:', error));
}