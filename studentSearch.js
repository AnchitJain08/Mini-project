function searchStudents() {
    let searchTerm = document.getElementById('searchTerm').value.toLowerCase();
    let results = students.filter(student => 
        student.studentName.toLowerCase().includes(searchTerm) ||
        student.studentNo.toLowerCase().includes(searchTerm) ||
        student.email.toLowerCase().includes(searchTerm) ||
        student.phone.toLowerCase().includes(searchTerm) ||
        student.course.toLowerCase().includes(searchTerm)
    );
    displaySearchResults(results);
}

function displaySearchResults(results) {
    let resultsDiv = document.getElementById('searchResults');
    if (results.length === 0) {
        resultsDiv.innerHTML = '<p class="text-red-500">No results found.</p>';
    } else {
        resultsDiv.innerHTML = results.map(student => 
            `<div class="border p-2 mb-2">
                <p><strong>Student No:</strong> ${student.studentNo}</p>
                <p><strong>Name:</strong> ${student.studentName}</p>
                <p><strong>Email:</strong> ${student.email}</p>
                <p><strong>Phone:</strong> ${student.phone}</p>
                <p><strong>Course:</strong> ${student.course}</p>
            </div>`
        ).join('');
    }
    resultsDiv.innerHTML += `
        <button onclick="clearSearchResults()" class="mt-4 bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded">
            Clear Results
        </button>`;
}

function clearSearchResults() {
    document.getElementById('searchTerm').value = '';
    document.getElementById('searchResults').innerHTML = '';
}
