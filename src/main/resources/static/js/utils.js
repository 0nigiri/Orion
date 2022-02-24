$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

});


function showPassword() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function contratoPorcentagem() {
    var x = document.getElementById("contrato");
    const y = x.options[x.selectedIndex].value
    if (y === "UJ") {
        document.getElementById("porcentagem").value = "10%";
        document.getElementById("numeroJogos").value = "1";
        document.getElementById("numeroJogos").readOnly = true;
    } else if (y === "MJ") {
        document.getElementById("porcentagem").value = "30%";
        document.getElementById("numeroJogos").readOnly = false;
    }
}

jQuery(function () {

    const checkboxes = jQuery('input[type="checkbox"]');

    checkboxes.click(function () {
        // let max = 3;
        let s = document.getElementById("numeroJogos").value;
        let max  = isNaN(parseInt(s)) ? 0 : parseInt(s);

        const $this = $(this);
        const set = checkboxes.filter('[name="' + this.name + '"]')
        const current = set.filter(':checked').length;
        return current <= max;
    });
});