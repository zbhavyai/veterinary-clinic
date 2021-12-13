import React, { Component } from 'react';
import axios from "axios";
import NavBar from './navbar';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";


class TreatmentList extends React.Component {
    state = {
        animal: {},
        treatments: [],
        imageUrl: process.env.PUBLIC_URL + '/toobad.png',
        apiphotos: [],
        photos:[],
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalTreatmentUrl = "http://localhost:8080/api/v1/animals/" + id + "/treatments"
        const { data: treatments } = await axios.get(animalTreatmentUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ treatments });

        var animalphotoUrl = "http://localhost:8080/api/v1/animals/" + id + "/photos"
        const { data: apiphotos } = await axios.get(animalphotoUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ apiphotos });
        
        var image = null;
        var photourl = null;
        var paray = [];
        for (var i = 0; i< this.state.apiphotos.length;i++){
            photourl = "http://localhost:8080/api/v1/animals/" + id+ "/photos/" + this.state.apiphotos[i]["photoId"];
            image = await axios.get(photourl, { headers: { 'Access-Control-Allow-Origin': true, }, });
            paray.push(photourl);

        }

        this.setState({ photos: paray});

        if(this.state.photos.length>0){
            this.setState({
                imageUrl: this.state.photos[0]
            })
        }
        

        console.log(this.state.photos.length)
    }


    // componentWillMount() {
    //     const id = this.props.match.params.id;
    //     // console.log(id);

    //     this.setState({
    //         animal: getAnimalbyId(id),
    //     });
    // }

    handleRemoveTreatments =(e, treatment)=>{
        const user = this.props.match.params.user;
        const id = this.props.match.params.id;
        const uid = this.props.match.params.uid;
        
        const link = "http://localhost:8080/api/v1/animals/" + id + "/treatments/" + treatment["treatmentId"];
        axios.delete(link).then(res => {
            console.log(res);
            console.log(res.data);
          });;

          const timer = setTimeout(() => {
            window.location.reload(false);
         }, 500);
        //window.location.reload(false);

        


    };

    render() {
        const user = this.props.match.params.user;
        const cid = this.props.match.params.cid;
        const uid = this.props.match.params.uid;

        if(user == "i" || user=="s"){
            return <React.Fragment>

<NavBar user = {user} uid = {uid}/>
            
            <h2 class="display-4">Treatment Logs</h2>
            <div class="row">
                <div class="col-sm">
                    <img src={this.state.imageUrl} alt="" />

                </div>
                <div class="col-sm">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h1 class="display-4">Basic Details</h1>
                            <p class="lead">Animal ID: {this.state.animal["animalId"]} <Link to={"/"+user+"/" + uid +"/animals/" + this.state.animal["animalId"] } className="btn btn-secondary btn-sm">Back to Profile</Link></p>
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
                        <h1 class="display-4">Log History </h1>
                        {this.state.treatments.map(treatment => (
                            <div class="card" style={this.styles}>
                                <p>Log {treatment["treatmentId"]}: {treatment["treatmentDate"]}</p>

                                <p>Decscription: {treatment["treatmentDesc"]}</p>
                               
                                 <p></p>
                                <p></p>
                            </div>
                        ))}
                    </div>
                </div>

            </div>
        </React.Fragment>;

        }else{
            return <React.Fragment>

            <NavBar user={user} />
            
            <h2 class="display-4">Treatment Logs</h2>
            <div class="row">
                <div class="col-sm">
                    <img src={this.state.imageUrl} alt="" />

                </div>
                <div class="col-sm">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h1 class="display-4">Basic Details</h1>
                            <p class="lead">Animal ID: {this.state.animal["animalId"]}<Link to={"/"+user+"/" + uid +"/animals/" + this.state.animal["animalId"] } className="btn btn-secondary btn-sm">Back to Profile</Link></p>
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
                        <h1 class="display-4">Log History <Link to={"/"+user+"/" + uid +"/animals/" + this.state.animal["animalId"] + "/treatments/add"} className="btn btn-secondary btn-sm">Add Treatment</Link></h1>
                        {this.state.treatments.map(treatment => (
                            <div class="card" style={this.styles}>
                                <p>Log {treatment["treatmentId"]}: {treatment["treatmentDate"]}</p>

                                <p>Decscription: {treatment["treatmentDesc"]}</p>
                                <td><Link to={"/animals/" + this.state.animal["animalId"].toString()} className="btn btn-secondary btn-sm">Edit</Link><button onClick={(e) => this.handleRemoveTreatments(e, treatment)} className="btn btn-secondary btn-sm">Remove</button></td>
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
}

export default TreatmentList;
