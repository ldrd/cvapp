// When the user clicks the button, open the modal 
function showEditProfile() {
	document.body.style.overflow = "hidden"
	document.getElementById('modal-profile').style.display = "block";
}

// When the user clicks on <span> (x), close the modal
function hideProfileModal() {
	document.body.style.overflow = "auto"
	document.getElementById('modal-profile').style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == document.getElementById('modal-profile')) {
	  document.body.style.overflow = "auto"
	  document.getElementById('modal-profile').style.display = "none";
  }
}