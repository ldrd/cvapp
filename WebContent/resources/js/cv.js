// When the user clicks the button, open the modal 
function showEditProfile() {
	document.body.style.overflow = "hidden"
	document.getElementById('modal-profile').style.display = "block";
}

function showAddPerson() {
	document.body.style.overflow = "hidden"
	document.getElementById('modal-person').style.display = "block";
}

function showEditActivity() {
	document.body.style.overflow = "hidden"
	document.getElementById('modal-activity').style.display = "block";
}

function hideActivityModal() {
	document.body.style.overflow = "auto"
	document.getElementById('modal-activity').style.display = "none";
}

function hidePersonModal() {
	document.body.style.overflow = "auto"
	document.getElementById('modal-person').style.display = "none";
}

function hideProfileModal() {
	document.body.style.overflow = "auto"
	document.getElementById('modal-profile').style.display = "none";
}

window.onclick = function(event) {
  if (event.target == document.getElementById('modal-profile')) {
	  document.body.style.overflow = "auto"
	  document.getElementById('modal-profile').style.display = "none";
  }
  if (event.target == document.getElementById('modal-activity')) {
	  document.body.style.overflow = "auto"
	  document.getElementById('modal-activity').style.display = "none";
  }
  if (event.target == document.getElementById('modal-person')) {
	  document.body.style.overflow = "auto"
	  document.getElementById('modal-person').style.display = "none";
  }
}

function openTab(evt, tabName) {
  var i;
  var x = document.getElementsByClassName("tab");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  
  var tablinks = document.getElementsByClassName("bar-item");
  for (i = 0; i < x.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" tab-active", "");
  }
  
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " tab-active";
}