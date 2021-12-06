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
class EditComment extends React.Component {
    state = {
        animal: {},
        comment: "",
        comments:[],
        placeholder: "",
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
        this.setState({placeholder: this.state.comments[0]["commentDesc"]});
        console.log(this.state.comments[0]["commentDesc"]);
    }

    handleChange(event) {
        this.setState({comment: event.target.value})
        
      }

    handleComment =(e)=>{
        const id = this.props.match.params.id;
        const user = this.props.match.params.user;

        let newDate = new Date()
        let date = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
        let day = newDate.getDay();
        let dateString = year.toString() + "-" + month.toString()+ "-"+day.toString();

        const message = {
            "commentId": this.state.comments[0]["commentId"],
            "commentDesc": this.state.comments[0]["commentDesc"],
            "commentDate": dateString,
            "commenter": {
                "userId": user
            }
        }
       
        
        const link = "http://localhost:8080/api/v1/animals/" + id + "/comments";
        axios.put(link, message,{headers:{}}).then(res => {
            console.log(res);
            console.log(res.data);
          });
        //window.location.reload(false);

        this.props.history.push("/"+ user+'/animals/'+id+"/comments")
        





    };

    render() {
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        
            return <React.Fragment>
            <NavBar user = {user} uid = {uid}/>
            
            <h2 class="display-4">Comment Logs</h2>
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
                        <h1 class="display-4">Edit Comment</h1>
                        <div class="row">
                        <div class="col-sm">
                            < input type="text" id="inputFilter" className="form-control" placeholder={this.state.placeholder} aria-label="First Name" aria-describedby="basic-addon2" value={this.state.comment} onChange={(e) =>this.handleChange(e)}/>
                            <button onClick={(e) => this.handleComment(e)} className="btn btn-primary">Edit Comment</button>
                        
                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </React.Fragment>;

        


        
    }
}
 
export default EditComment;