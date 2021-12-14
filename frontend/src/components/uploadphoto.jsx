import React from 'react';
import axios from "axios";

import NavBar from './navbar';

class UploadPhoto extends React.Component {
    state = {
        animal: {},
        comment: "",
        imageUrl: process.env.PUBLIC_URL + '/toobad.png',
        apiphotos: [],
        photos:[],
        selectedFile: null
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });


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

    onFileChange = event => {
        
        this.setState({ selectedFile: event.target.files[0] });
      
      };
      
      
      onFileUpload = () => {
        const id = this.props.match.params.id;
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;

        const formData = new FormData();
    
        
        formData.append(
          "image", this.state.selectedFile 
          
        );
        formData.append("userId", uid);
        formData.append("photodesc", "photoupload");
        formData.append("alttext", "photoupload");

      
        

        const link = "http://localhost:8080/api/v1/animals/" + id + "/photos";
        axios.post(link, formData,{headers:{}}).then(res => {
            console.log(res);
            console.log(res.data);
          });
        //window.location.reload(false);

        setTimeout(() => {
            this.props.history.push("/"+ user+"/" + uid +'/animals/'+id);
         }, 500);
      };

      fileData = () => {
    
        if (this.state.selectedFile) {
           
          return (
            <div>
              <h2>File Details:</h2>
               
                <p>File Name: {this.state.selectedFile.name}</p>
   
               
                <p>File Type: {this.state.selectedFile.type}</p>
   
               
            
   
            </div>
          );
        } else {
          return (
            <div>
              <br />
              <h4>Please click Choose and select file before clicking the Upload button</h4>
            </div>
          );
        }
      };

    
    render() {
        let newDate = new Date()
        let day = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
        
        let dateString = year.toString() + "-" + month.toString()+ "-"+day.toString();
        console.log(dateString);
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        
            return <React.Fragment>
            <NavBar user = {user} uid = {uid}/>
            
            <h2 className="display-4">Upload Photo</h2>
            <div className="row">
                <div className="col-sm">
                    <img src={this.state.imageUrl} width="300" 
                                height="300" alt="" />

                </div>
                <div className="col-sm">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h1 className="display-4">Basic Details</h1>
                            <p className="lead">Animal ID: {this.state.animal["animalId"]}</p>
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
                        <h1 className="display-4">Please follow instructions below:</h1>
                        <div className="row">
                        <div className="col-sm">
                        <div>
                                <input type="file" onChange={this.onFileChange} />
                                <button className="btn btn-secondary" onClick={this.onFileUpload}>
                                Upload!
                                </button>
                            </div>
                        {this.fileData()}
                            
                        
                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </React.Fragment>;

        


        
    }
}
 
export default UploadPhoto;