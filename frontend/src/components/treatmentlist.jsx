import React from 'react';
import axios from "axios";
import NavBar from './navbar';
import {
    
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
        
        
        var photourl = null;
        var paray = [];
        for (var i = 0; i< this.state.apiphotos.length;i++){
            photourl = "http://localhost:8080/api/v1/animals/" + id+ "/photos/" + this.state.apiphotos[i]["photoId"];
            
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
        
        const id = this.props.match.params.id;
        
        
        const link = "http://localhost:8080/api/v1/animals/" + id + "/treatments/" + treatment["treatmentId"];
        axios.delete(link).then(res => {
            console.log(res);
            console.log(res.data);
          });;

          setTimeout(() => {
            window.location.reload(false);
         }, 500);
        //window.location.reload(false);

        


    };

    render() {
        const user = this.props.match.params.user;
        
        const uid = this.props.match.params.uid;
        console.log("UID: " + uid);

        if(user == "i" || user=="s"){
            return <React.Fragment>

                <NavBar user = {user} uid = {uid}/>
            
            <h2 className="display-4">Treatment Logs</h2>
            <div className="row">
                <div className="col-sm">
                    <img src={this.state.imageUrl} width="300" 
                                height="300" alt="" />

                </div>
                <div className="col-sm">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h1 className="display-4">Basic Details</h1>
                            <p className="lead">Animal ID: {this.state.animal["animalId"]} <Link to={"/"+user+"/" + uid +"/animals/" + this.state.animal["animalId"] } className="btn btn-secondary btn-sm">Back to Profile</Link></p>
                                <p className="lead">Name: {this.state.animal["name"]}</p>
                                <p className="lead">Breed: {this.state.animal["breed"]}</p>
                                <p className="lead">Age: {this.state.animal["age"]}</p>
                                <p className="lead">Sex: {this.state.animal["sex"]}</p>
                                <p className="lead">Status: {this.state.animal["status"]}</p>
                        </div>
                    </div>
                </div>

            </div>

            <div className="row">
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Log History </h1>
                        {this.state.treatments.map(treatment => (
                            <div key = {treatment["treatmentId"]} className="card" style={this.styles}>
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
            
            <h2 className="display-4">Treatment Logs</h2>
            <div className="row">
                <div className="col-sm">
                    <img src={this.state.imageUrl} width="300" 
                                height="300" alt="" />

                </div>
                <div className="col-sm">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h1 className="display-4">Basic Details</h1>
                            <p className="lead">Animal ID: {this.state.animal["animalId"]}<Link to={"/"+user+"/" + uid +"/animals/" + this.state.animal["animalId"] } className="btn btn-secondary btn-sm">Back to Profile</Link></p>
                                <p className="lead">Name: {this.state.animal["name"]}</p>
                                <p className="lead">Breed: {this.state.animal["breed"]}</p>
                                <p className="lead">Age: {this.state.animal["age"]}</p>
                                <p className="lead">Sex: {this.state.animal["sex"]}</p>
                                <p className="lead">Status: {this.state.animal["status"]}</p>
                        </div>
                    </div>
                </div>

            </div>

            <div className="row">
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Log History <Link to={"/"+user+"/" + uid +"/animals/" + this.state.animal["animalId"] + "/treatments/add"} className="btn btn-secondary btn-sm">Add Treatment</Link></h1>
                        {this.state.treatments.map(treatment => (
                            <div key = {treatment["treatmentId"]} className="card" style={this.styles}>
                                <p>Log {treatment["treatmentId"]}: {treatment["treatmentDate"]}</p>

                                <p>Decscription: {treatment["treatmentDesc"]}</p>
                                <p><Link to={"/"+user+"/" + uid +"/animals/" + this.state.animal["animalId"].toString()+"/treatments/"+treatment["treatmentId"] + "/edit"} className="btn btn-secondary btn-sm">Edit</Link><button onClick={(e) => this.handleRemoveTreatments(e, treatment)} className="btn btn-secondary btn-sm">Remove</button></p>
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
