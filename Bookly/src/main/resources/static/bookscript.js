/**
 * 
 */
function getBook(id) {
    fetch("http://localhost:8080/bookdata/" + id)
        .then(data => data.json())
        .then(function(book) {
            showBook(book, id);
        });
}

function showBook(book, id) {

    var details =
        "<br/>Title: " + book.title +
        "<br/>Authors: " + book.authors;

    fetch("http://localhost:8080/reviews/" + id)
        .then(data => data.json())
        .then(function(reviews) {

            var reviewList = "<h3>Reviews</h3>";

            for (var i = 0; i < reviews.length; i++) {
                reviewList += "<br/>- " + reviews[i].review;
            }

            var reviewForm =
                "<h3>Add Review</h3>" +
                "<form action='/saveReview' method='post'>" +
                "<input type='hidden' name='bookid' value='" + id + "' />" +
                "Review: <input type='text' name='review' />" +
                "<button type='submit'>Add Review</button>" +
                "</form>";

            document.getElementById("bookdetails").innerHTML =
                details + reviewList + reviewForm;
        });
}