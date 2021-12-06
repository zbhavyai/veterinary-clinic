import React, { Component } from 'react';
import axios from "axios";
import ReactApexChart from 'react-apexcharts';
import { useParams } from "react-router-dom";
import { withRouter } from "react-router";
import NavBar from './navbar';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

class AnimalProfile extends React.Component {
    state = {
        animal: {},
        weights: [],
        owner: {},
        imageUrl: 'https://picsum.photos/200',
    };

    styles = {
        width: 18 + 'rem'
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalWeightUrl = "http://localhost:8080/api/v1/animals/" + id + "/weights"
        const { data: weights } = await axios.get(animalWeightUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ weights });

        var ownerId = this.state.animal["ownerId"];
        var ownerUrl = "http://localhost:8080/api/v1/owners/" + ownerId;
        const { data: owner } = await axios.get(ownerUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ owner });

        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
    }

    //WARNING! To be deprecated in React v17. Use componentDidMount instead.
    // componentWillMount() {
    //     const id = this.props.match.params.id;
    //     // console.log(id);

    //     this.setState({
    //         animal: getAnimalbyId(id),
    //     });
    // }

    render() {
        const user = this.props.match.params.user;
        const id = this.props.match.params.id;
        const uid = this.props.match.params.uid;
        let weight = [];
        let dates = [];
        // console.log(this.state.animal["weight"]);

        if (this.state.weights.length > 0) {
            for (var i = 0; i < this.state.weights.length; i++) {
                weight.push(this.state.weights[i]["massInKg"]);
                dates.push(this.state.weights[i]["recordDate"]);
            }
        }

        else {
            weight = [0, 0, 0, 0, 0, 0, 0, 0, 0];
            dates = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'];
        }

        const series = [{
            name: "Weight",
            data: weight
        }];

        const options = {
            chart: {
                height: 350,
                type: 'line',
                zoom: {
                    enabled: false
                }
            },
            dataLabels: {
                enabled: false
            },
            stroke: {
                curve: 'straight'
            },

            grid: {
                row: {
                    colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                    opacity: 0.5
                },
            },
            xaxis: { categories: dates }
        }

        if(user == "t" || user == "at"){
            return <React.Fragment>

            <NavBar user = {user} uid = {uid}/>

            <div class="container">
                <div class="row">
                    <div class="col-sm">
                        <img src={this.state.imageUrl} alt="" />

                    </div>
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Basic Details</h5>
                                <p class="lead">Animal ID: {this.state.animal["animalId"]}</p>
                                <p class="lead">Name: {this.state.animal["name"]}</p>
                                <p class="lead">Breed: {this.state.animal["breed"]}</p>
                                <p class="lead">Age: {this.state.animal["age"]}</p>
                                <p class="lead">Sex: {this.state.animal["sex"]}</p>
                                <p class="lead">Status: {this.state.animal["status"]}  <Link to={"/" + user + "/animals/" + id + "/status"} className="btn btn-secondary">Update</Link></p>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Owner Details</h5>
                                <p class="lead">Owner Name: {this.state.owner["fullName"]}</p>
                                <p class="lead">Owner Contact: {this.state.owner["contactNumber"]}</p>
                                <p class="lead">Owner Email: {this.state.owner["emailId"]}</p>
                                <p class="lead">Owner Address: {this.state.owner["address"]}</p>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Identification Details</h5>
                                <p class="lead">RFID Number: {this.state.animal["rfidNumber"]}</p>
                                <p class="lead">Microchip: {this.state.animal["microChipNumber"]}</p>
                                <p class="lead">Tattoo: {this.state.animal["tattooNum"]}</p>
                                <p class="lead">Coat Color: {this.state.animal["coatColor"]}</p>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Weight Graph</h5>
                            <div id="chart">
                                <ReactApexChart options={options} series={series} type="line" height={350} />
                            </div>


                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Other Details</h5>
                            <p class="lead">Continuous Medication: {this.state.animal["continuousMedication"]}</p>


                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Photos</h5>


                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <td><Link to={"/" + user + "/" + uid +"/animals/" + id + "/treatments"} className="btn btn-secondary">Treatment List</Link></td>
                        <td><Link to={"/" + user + "/" + uid +"/animals/" + id + "/issues"} className="btn btn-secondary">Issue List</Link></td>
                        <td><Link to={"/" + user + "/" + uid +"/animals/" + id + "/comments"} className="btn btn-secondary">Comment List</Link></td> 
                    </div>

                </div>
            </div>



        </React.Fragment>;

        }
        else{
            return <React.Fragment>

            <NavBar user = {user} uid = {uid}/>

            <div class="container">
                <div class="row">
                    <div class="col-sm">
                        <img src={this.state.imageUrl} alt="" />

                    </div>
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Basic Details</h5>
                                <p class="lead">Animal ID: {this.state.animal["animalId"]}</p>
                                <p class="lead">Name: {this.state.animal["name"]}</p>
                                <p class="lead">Breed: {this.state.animal["breed"]}</p>
                                <p class="lead">Age: {this.state.animal["age"]}</p>
                                <p class="lead">Sex: {this.state.animal["sex"]}</p>
                                <p class="lead">Status: {this.state.animal["status"]}</p>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Owner Details</h5>
                                <p class="lead">Owner Name: {this.state.owner["fullName"]}</p>
                                <p class="lead">Owner Contact: {this.state.owner["contactNumber"]}</p>
                                <p class="lead">Owner Email: {this.state.owner["emailId"]}</p>
                                <p class="lead">Owner Address: {this.state.owner["address"]}</p>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Identification Details</h5>
                                <p class="lead">RFID Number: {this.state.animal["rfidNumber"]}</p>
                                <p class="lead">Microchip: {this.state.animal["microChipNumber"]}</p>
                                <p class="lead">Tattoo: {this.state.animal["tattooNum"]}</p>
                                <p class="lead">Coat Color: {this.state.animal["coatColor"]}</p>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Weight Graph</h5>
                            <div id="chart">
                                <ReactApexChart options={options} series={series} type="line" height={350} />
                            </div>


                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Other Details</h5>
                            <p class="lead">Continuous Medication: {this.state.animal["continuousMedication"]}</p>


                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h5 class="display-4">Photos</h5>


                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <td><Link to={"/" + user + "/" + uid +"/animals/" + id + "/treatments"} className="btn btn-secondary">Treatment List</Link></td>
                        <td><Link to={"/" + user + "/" + uid +"/animals/" + id + "/issues"} className="btn btn-secondary">Issue List</Link></td>
                        <td><Link to={"/" + user + "/" + uid +"/animals/" + id + "/comments"} className="btn btn-secondary">Comment List</Link></td> 
                    </div>

                </div>
            </div>



        </React.Fragment>;

        }


        
    }
}

export default withRouter(AnimalProfile);