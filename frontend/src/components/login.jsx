

import React, { Component } from 'react';
import NavBarStart from './navbarstart';
class Login extends React.Component {
    render() { 
        return <React.Fragment>
            <NavBarStart/>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-2"></div>
                    <div class="col-lg-6 col-md-8 login-box">                        
                        <div class="col-lg-12 login-title">
                            Sign in to your account
                        </div>

                        <div class="login-form">
                            
                                <form>
                                    <div class="form-group">
                                        <label class="form-control-label">Username</label>
                                        <input type="text" class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-control-label">Password</label>
                                        <input type="password" class="form-control" i/>
                                    </div>

                                    <div >
                                        <a href="/menu" class="btn btn-primary">Login</a>
                                        <button type="submit" class="btn btn-outline-primary">Forget Password</button>
                                        
                                    </div>
                                </form>
                            
                        </div>
                        
                    </div>
                </div>
            </div>



            </React.Fragment>;
    }
}
 
export default Login;