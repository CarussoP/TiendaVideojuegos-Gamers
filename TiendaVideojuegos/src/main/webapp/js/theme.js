(function () {
	var STORAGE_KEY = "gamers-theme";

	function isDark() {
		return document.documentElement.classList.contains("dark-mode");
	}

	function applyTheme(dark) {
		document.documentElement.classList.toggle("dark-mode", dark);
		try {
			localStorage.setItem(STORAGE_KEY, dark ? "dark" : "light");
		} catch (e) { /* ignore */ }
		var toggle = document.getElementById("themeToggle");
		if (toggle) {
			toggle.checked = dark;
		}
	}

	function init() {
		var toggle = document.getElementById("themeToggle");
		if (!toggle) {
			return;
		}
		toggle.checked = isDark();
		toggle.addEventListener("change", function () {
			applyTheme(toggle.checked);
		});
	}

	if (document.readyState === "loading") {
		document.addEventListener("DOMContentLoaded", init);
	} else {
		init();
	}
})();
