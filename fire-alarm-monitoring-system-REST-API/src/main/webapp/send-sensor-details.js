const sensor = {
    id: 20,
    isActive: true,
    location:'F4-R4',
    smokeLvl: 6,
    CO2Lvl: 4
};

sendDetails();
function sendDetails(){
    jQuery.ajax({
        url: "http://localhost:8080/rest/webapi/sensors/sensor",
        type: "PUT",
        contentType: "application/json",
        dataType:'json',
        success: function(sensor, textStatus, errorThrown) {
            console.log('success');
            //here is your json.
            // process it
            // $("#title").text(data.title);
            // $("#price").text(data.price);

        },
        error : function(jqXHR, textStatus, errorThrown) {
            // $("#title").text("Sorry! Book not found!");
            // $("#price").text("");
        	console.log(errorThrown);
        },
        timeout: 120000,
    });
}