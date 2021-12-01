import React, { Component } from 'react';
import axios from "axios";
import {getAnimalbyId} from '../services/fakeAnimalsService'; 
import ReactApexChart from 'react-apexcharts';
import {useParams} from "react-router-dom";
import { withRouter } from "react-router";
import NavBar from './navbar';

class AnimalProfile extends React.Component {
    state = {
        animal: {},
        
        
        imageUrl: 'https://picsum.photos/200',

        
        
        
    };

    async componentDidMount() {
        
        const {data: posts} = await axios.get('http://localhost:8080/api/v1/animals/1', {headers: {'Access-Control-Allow-Origin': true,},});
        
        this.setState({posts});
        

        

        
            
        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
        
    }

    //WARNING! To be deprecated in React v17. Use componentDidMount instead.
    componentWillMount() {
        const id = this.props.match.params.id;
        console.log(id);


        this.setState({animal: getAnimalbyId(id), 
            
        });

        
        

        
        

    }

    render() { 
        let weight = [];
        let dates = [];
        console.log(this.state.animal["weight"]);

        if(this.state.animal["weight"].length>0){

            for(var i = 0; i<this.state.animal["weight"].length; i++){
                weight.push(this.state.animal["weight"][i]["value"]);
                dates.push(this.state.animal["weight"][i]["date"]);
                
                

            }

            
            
           

        }
        else{
            weight = [0, 0, 0, 0, 0, 0, 0, 0, 0];
            dates = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'];

            
        }
        const series= [{
            name: "Weight",
            data: weight
        }];

        const options= {
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
            xaxis: {categories: dates}
          }


        return <React.Fragment>
            <NavBar/>
            <div class="container">
                <div class="row">
                    <div class="col-sm">
                    <img src={this.state.imageUrl} alt=""/>

                    </div>
                    <div class="col-sm">
                    <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h5 class="display-4">Basic Details</h5>
                        <p class="lead">Animal ID: {this.state.animal["animalId"]}</p>
                        <p class="lead">Name: {this.state.animal["name"]}</p>
                        <p class="lead">Breed: {this.state.animal["breed"]}</p>
                        <p class="lead">Age: {this.state.animal["age"]}</p>
                        <p class="lead">Sex: {this.state.animal["animalId"]}</p>
                        <p class="lead">Status: {this.state.animal["sex"]}</p>
                    </div>
                    </div>
                    </div>
                    
                </div>
                <div class="row">
                    <div class="col-sm">
                        <div class="jumbotron jumbotron-fluid">
                            <div class="container">
                                <h5 class="display-4">Owner Details</h5>
                                <p class="lead">Owner Name: {this.state.animal["theOwner"]["firstName"]} {this.state.animal["theOwner"]["lastName"]}</p>
                                <p class="lead">Owner Contact: {this.state.animal["theOwner"]["contactNumber"]}</p>
                                <p class="lead">Owner Email: {this.state.animal["theOwner"]["emailId"]}</p>
                                <p class="lead">Owner Address: {this.state.animal["theOwner"]["address"]}</p>
                                
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
                <button type="button" class="btn btn-secondary">Treat List</button>
                <button type="button" class="btn btn-secondary">Issue List</button>
                <button type="button" class="btn btn-secondary">Comment List</button>
                </div>

                </div>   
            </div>
            


            </React.Fragment>;
    }
}
 
export default withRouter(AnimalProfile);