import React, { Component } from 'react';
import axios from "axios";
import ReactApexChart from 'react-apexcharts';
import { useParams } from "react-router-dom";
import { withRouter } from "react-router";
import NavBar from './navbar';
import { Redirect } from 'react-router';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";


class AddIssue extends React.Component {
    state = {
        animal: {},
        comment: "",
        imageUrl: 'https://picsum.photos/200',
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalCommentUrl = "http://localhost:8080/api/v1/animals/" + id + "/comments"
        const { data: comments } = await axios.get(animalCommentUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ comments });
    }

    handleChange(event) {
        this.setState({comment: event.target.value})
        
      }

    handleComment =(e)=>{
        const id = this.props.match.params.id;
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        let newDate = new Date()
        let day = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
        
        let dateString = year.toString() + "-" + month.toString()+ "-"+day.toString();
        console.log(dateString);

        const message = {
            "issueId": 1,
            "issueDesc": this.state.comment,
            "detectedDate": "2021-12-08",
            "resolved": true,
            "raisedBy": {
                "userId": uid
            }
        }

        
       
        
        let link = "http://localhost:8080/api/v1/animals/" + id + "/issues";
        axios.post(link, message,{headers:{}}).then(res => {
            console.log(res);
            console.log(res.data);
          });
        

        const status = {"status": "RED"};
        link = "http://localhost:8080/api/v1/animals/" + id;
        axios.put(link, status,{headers:{}});

        this.props.history.push("/"+ user+"/" + uid +'/animals/'+id+"/issues")
        





    };

    render() {
        let newDate = new Date()
        let day = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
        
        let dateString = year.toString() + "-" + month.toString()+ "-"+day.toString();
        console.log(dateString);
        const user = this.props.match.params.user;
        const uid  = this.props.match.params.uid;
        
            return <React.Fragment>
            <NavBar user = {user} uid = {uid}/>
            
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
                        <h1 class="display-4">Add Issue</h1>
                        <div class="row">
                        <div class="col-sm">
                            < input type="text" id="inputFilter" className="form-control" placeholder="Add your comment here" aria-label="First Name" aria-describedby="basic-addon2" value={this.state.comment} onChange={(e) =>this.handleChange(e)}/>
                            <button onClick={(e) => this.handleComment(e)} className="btn btn-primary">Add Issue</button>
                        
                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </React.Fragment>;

        


        
    }
}
 
export default AddIssue;