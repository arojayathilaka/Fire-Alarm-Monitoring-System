import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import './alarm-status.css'

class App extends Component{

  constructor() {
      super();
      this.state = {
          sensorDetails: []
      };
  }

  componentDidMount() {
      this.loadSensorDetails(); // initially call loadSensorDetails()
      setInterval(this.loadSensorDetails, 40000); // call loadSensorDetails() after every 40 seconds
  }

    // method to get sensor details of all the sensors
    loadSensorDetails = async () => {
        try {
            const res = await axios.get('http://localhost:8080/rest/webapi/sensors'); // make a GET request to get all the sensor details
            const sensorDetails = res.data; // assign res.data to sensorDetails
            this.setState({
                sensorDetails: sensorDetails // set state
            });
            console.log(this.state.sensorDetails);
        } catch (err) {
            console.log(err) // log errors if there are any
        }
    };

  // updateSensorDetails = async () => {
  //     const ops = [];
  //     this.state.sensorDetails.forEach(sensor => {
  //         const no = Math.random();
  //         if (no > 0.5) {
  //             sensor.active = !sensor.active;
  //             if (!sensor.active) {
  //                 sensor.smokeLvl = -1;
  //                 sensor.CO2Lvl = -1;
  //             } else {
  //                 sensor.smokeLvl = this.randomIntFromInterval(1, 10);
  //                 sensor.CO2Lvl = this.randomIntFromInterval(1, 10);
  //             }
  //         } else {
  //             if (!sensor.active) {
  //                 sensor.smokeLvl = -1;
  //                 sensor.CO2Lvl = -1;
  //             } else {
  //                 sensor.smokeLvl = this.randomIntFromInterval(1, 10);
  //                 sensor.CO2Lvl = this.randomIntFromInterval(1, 10);
  //             }
  //         }
  //         const op =  axios.put('http://localhost:8080/rest/webapi/sensors/sensor', sensor);
  //         ops.push(op);
  //     });
  //
  //     try {
  //         const res = await axios.all(ops);
  //         console.log(res);
  //     } catch (err) {
  //         console.log(err);
  //     }
  // };

  randomIntFromInterval(min, max) { // min and max included
        return Math.floor(Math.random() * (max - min + 1) + min);
  }

  render() {
    return (
        <div className="body">
          <div className="container mt-3">
              <h3 style={{textAlign: "center"}}>Online Fire Alarm Monitoring System</h3>
              <table className="table table-hover table-dark mt-4" >
                  <thead>
                  <tr>
                      <th scope="col">Active</th>
                      <th scope="col">Location</th>
                      <th scope="col">Smoke Level</th>
                      <th scope="col">CO2 Level</th>
                  </tr>
                  </thead>
                  <tbody>
                  {this.state.sensorDetails.map(sensor => (
                      <tr key={sensor.id}>
                          <td>{sensor.active.toString()}</td>
                          <td>{sensor.location}</td>

                          {/*if the smoke level is greater than 5 className is red, if the smoke level is equal to -1 className is signal,
                          else className is green*/}
                          <td className={sensor.smokeLvl > 5 ? "red" : sensor.smokeLvl === -1 ? "signal" : "green"}>
                              <b>{sensor.smokeLvl === -1 ? "No signal" : sensor.smokeLvl}</b>
                          </td>

                          {/*if the C02 level is greater than 5 className is red, if the C02 level is equal to -1 className is signal,
                          else className is green*/}
                          <td className={sensor.CO2Lvl > 5 ? "red" : sensor.CO2Lvl === -1 ? "signal" : "green"}>
                              <b>{sensor.CO2Lvl === -1 ? "No signal" : sensor.CO2Lvl}</b>
                          </td>
                      </tr>
                      ))}
                  </tbody>
              </table>
          </div>
        </div>
    );
  }
}

export default App;