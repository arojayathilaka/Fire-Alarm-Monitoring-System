sendDetails(); // initially call sendDetails()
setInterval(sendDetails, 10000); // call sendDetails() after every 10 seconds

function sendDetails(){

    const no = Math.random(); // generates a random number in the range of 0 to less than 1
    const active = no > 0.5;
    let CO2Lvl;
    let smokeLvl;
    if (active) { // if the sensor is active, assign a random number between 1 to 10 for CO2 level and smoke level
        CO2Lvl = randomInt(1, 10);
        smokeLvl = randomInt(1, 10);
    } else { // if the sensor is not active, consider CO2 level and smoke level as -1
        CO2Lvl = -1;
        smokeLvl = -1;
    }

    const sensor = { // create a sensor object with updated sensor details
        CO2Lvl: CO2Lvl,
        active: active,
        id: 4,
        location: "F2-R5",
        smokeLvl: smokeLvl
    };

    // ajax request to update sensor details
    jQuery.ajax({
        url: "http://localhost:8080/rest/webapi/sensors/update",
        type: "PUT",
        contentType: "application/json",
        dataType:'json',
        data: JSON.stringify(sensor),
        success: function(data, textStatus, errorThrown) {

            $("#location").text(data.location);

            if (data.CO2Lvl === -1)
                $("#CO2Lvl").text("No signal");
            else
                $("#CO2Lvl").text(data.CO2Lvl);

            if (data.smokeLvl === -1)
                $("#smokeLvl").text("No signal");
            else
                $("#smokeLvl").text(data.smokeLvl);

            $("#active").text(data.active);

        },
        error : function(jqXHR, textStatus, errorThrown) {
        },
        timeout: 120000,
    });
}

// function to generate a random number between 1 and 10
function randomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}
