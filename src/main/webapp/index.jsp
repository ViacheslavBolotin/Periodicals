<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<div class="container">
<br>
<div class="row">
	<aside class="col-sm-4">
<p>Periodicals</p>
<div class="card">
<article class="card-body">
<a href="" class="float-right btn btn-outline-primary">Sign up</a>
<h4 class="card-title mb-4 mt-1">Sign in</h4>
	 <form action = "/Periodicals/front" method ="post">
    <div class="form-group">
    	<label>Your name</label>
        <input name="fname" class="form-control" placeholder="name">
    </div> <!-- form-group// -->

    <div class="form-group">
       	<label>Last name</label>
        <input name="lname" class="form-control" placeholder="last name">
    </div> <!-- form-group// -->

    <div class="form-group">
    	<label>Password</label>
        <input name="password" class="form-control" placeholder="******" type="password">
    </div> <!-- form-group// -->
    <div class="form-group">
    <div class="checkbox">
      <label> <input type="checkbox"> Save password </label>
    </div> <!-- checkbox .// -->
    </div> <!-- form-group// -->
    <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block"> Login  </button>
    </div> <!-- form-group// -->
</form>
</article>
</div> <!-- card.// -->

	</aside> <!-- col.// -->
	<aside class="col-sm-4">