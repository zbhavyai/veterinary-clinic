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
class AnimalStatus extends React.Component {
    state = {
        animal: {},
        weights: [],
        owner: {},
        imageUrl: 'https://picsum.photos/200',
    };

    styles = {
        width: 18 + 'rem'
    };

    handleStatus =(e, statusx)=>{
        const id = this.props.match.params.id;
       
        const status = {"status": statusx};
        const link = "http://localhost:8080/api/v1/animals/" + id;
        axios.put(link, status,{headers:{}});
        window.location.reload(false);
        





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

    render() { 
        const user = this.props.match.params.user;
        const id = this.props.match.params.id;
        const uid = this.props.match.params.uid;

        return <React.Fragment>
            
            <NavBar user = {user} uid = {uid}/>

            <div class="row">
                <div class="col-sm">
                    <img src={this.state.imageUrl} alt="" />

                </div>
                <div class="col-sm">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h1 class="display-4">Basic Details</h1>
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
                <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h1 class="display-4">Change Status to:</h1>
                        <row>
                        <button onClick={(e) => this.handleStatus(e, "GREEN")} className="btn btn-primary">Green</button><button onClick={(e) => this.handleStatus(e, "YELLOW")} className="btn btn-primary ">Yellow</button><button onClick={(e) => this.handleStatus(e, "RED")} className="btn btn-primary ">Red</button><button onClick={(e) => this.handleStatus(e, "INACTIVE")} className="btn btn-primary">Inactive</button>
                        </row>
                        
                    </div>
                </div>

            </div>

        </React.Fragment>;
    }
}
 
export default AnimalStatus;