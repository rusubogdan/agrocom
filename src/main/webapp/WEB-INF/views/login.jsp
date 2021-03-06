<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container login-container">
    <div id="login-box" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
                <div class="forgot-password">
                    <a href="#">Forgot password?</a>
                </div>
            </div>

            <div class="panel-body">
                <div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                <form id="login-form" action="/j_spring_security_check"
                      class="form-horizontal" role="form" method="post">
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="j_username" type="text" class="form-control" name="j_username" value=""
                               placeholder="username or email">
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="j_password" type="password" class="form-control" name="j_password"
                               placeholder="password">
                    </div>

                    <%--<div class="input-group">
                        <div class="checkbox">
                            <label>
                                <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                            </label>
                        </div>
                    </div>--%>

                    <div style="margin-top:10px" class="form-group">
                        <div class="col-sm-12 controls">
                            <button id="btn-login" type="submit" class="btn btn-success">
                                Login
                            </button>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-12 control">
                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                Don't have an account!
                                <a href="#" onClick="$('#login-box').hide(); $('#sign-up-box').show()">
                                    Sign Up Here
                                </a>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div id="sign-up-box" style="display:none; margin-top:50px"
         class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign Up</div>
                <div style="float:right; font-size: 85%; position: relative; top:-10px">
                    <a id="signinlink" href="#" onclick="$('#sign-up-box').hide(); $('#login-box').show()">
                        Sign In
                    </a>
                </div>
            </div>
            <div class="panel-body">
                <form id="signupform" action="/register"
                      class="form-horizontal" role="form" method="POST">
                    <div id="signupalert" style="display:none" class="alert alert-danger">
                        <p>Error:</p>
                        <span></span>
                    </div>

                    <div class="form-group">
                        <label for="first-name" class="col-md-3 control-label">First Name</label>
                        <div class="col-md-9">
                            <input id="first-name" type="text" class="form-control" name="first-name" placeholder="First Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="last-name" class="col-md-3 control-label">Last Name</label>

                        <div class="col-md-9">
                            <input id="last-name" type="text" class="form-control" name="last-name" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-3 control-label">Email</label>
                        <div class="col-md-9">
                            <input id="email" type="text" class="form-control" name="email" placeholder="Email Address">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pin" class="col-md-3 control-label">PIN</label>
                        <div class="col-md-9">
                            <input id="pin" type="text" class="form-control" name="pin"
                                   placeholder="PIN">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirm-password" class="col-md-3 control-label">Confirm password</label>

                        <div class="col-md-9">
                            <input id="confirm-password" type="password" class="form-control" name="confirm-password"
                                   placeholder="Confirm password">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i>
                                &nbsp Sign Up
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>