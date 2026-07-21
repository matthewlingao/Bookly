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
			var totalRating = 0;

            for (var i = 0; i < reviews.length; i++) {
				
				totalRating += reviews[i].rating;
				
                reviewList += 
					"<br />Rating: " + reviews[i].rating + "/5<br />" +
					"<br />Review: " + reviews[i].review + 
					"<br />";
				
            }
			
			var averageRating = 0;
			
			if (reviews.length > 0) {
				averageRating = totalRating / reviews.length;
			}
			
			var averageRatingDisplay = 
				"<h3>Average Rating: " + averageRating.toFixed(1) + "/ 5</h3>"
				reviewList;

            var reviewForm =
                "<h3>Add Review</h3>" +
                "<form action='/saveReview' method='post'>" +
                "<input type='hidden' name='bookid' value='" + id + "' />" +
				
				"Rating: " +
				"<select name='rating'>" +
					"<option value='5'>5 Stars</option>" +
					"<option value='4'>4 Stars</option>" +
					"<option value='3'>3 Stars</option>" +
					"<option value='2'>2 Stars</option>" +
					"<option value='1'>1 Star</option>" +
				"/select><br />" +
				
                "Review: <input type='text' name='review' />" +
                "<button type='submit'>Add Review</button>" +
                "</form>";

            document.getElementById("bookdetails").innerHTML =
                details + averageRatingDisplay + reviewList + reviewForm;
        });
}