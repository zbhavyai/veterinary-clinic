import React, { Component } from 'react';
import axios from "axios";
import NavBar from './navbar';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";


class IssueList extends React.Component {
    state = {
        animal: {},
        issues: [],
        imageUrl: 'https://picsum.photos/200',
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalIssueUrl = "http://localhost:8080/api/v1/animals/" + id + "/issues"
        const { data: issues } = await axios.get(animalIssueUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ issues });
    }


    // componentWillMount() {
    //     const id = this.props.match.params.id;
    //     // console.log(id);

    //     this.setState({
    //         animal: getAnimalbyId(id),
    //     });
    // }

    render() {
        const user = this.props.match.params.user;
        console.log(this.state.issues);


        return <React.Fragment>
            <NavBar user={user} /> 
            
            <h2 class="display-4">Issue Logs</h2>
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
                        <h1 class="display-4">Log History</h1>
                        {this.state.issues.map(issue => (
                            <div class="card" style={this.styles}>
                                <p>Log {issue["issueId"]}: {issue["issueDate"]}</p>

                                <p>{issue["issueDesc"]}</p>
                                <td><Link to={"/" + user+ "/animals/" + this.state.animal["animalId"].toString()} className="btn btn-secondary btn-sm">Edit</Link><Link to={"/animals/" + this.state.animal["animalId"].toString()} className="btn btn-secondary btn-sm">Remove</Link></td>
                                <p></p>
                                <p></p>
                            </div>
                        ))}
                    </div>
                </div>

            </div>
        </React.Fragment>;
    }
}

export default IssueList;
