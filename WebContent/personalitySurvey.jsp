<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Stulance - Get your Job done</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/forwards/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="http://stulance.mybluemix.net/forwards/css/shop-homepage.css" rel="stylesheet">
    <link href="http://stulance.mybluemix.net/css/freelancer.css" rel="stylesheet">
</head>

<body>

  <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">Stulance</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	<hr>
    <!-- Page Content -->
    <div class="container">

        <div class="row">
			
            <div class="col-md-3">
                <p class="lead" style="color:#2c3e50"><b>Categories</b></p>
                <div class="list-group">
                	<h5><a href = "<%=request.getContextPath()%>/get/jobs/?category=itprogramming&field=all">IT programming</a></h5>
                </div>
				
				<div class="list-group">
                	<h5><a href = "/stulance/get/jobs/?category=designmultimedia&field=all">Design & Multimedia</h5>
                </div>
				
				<div class="list-group">
                	<h5><a href = "/stulance/get/jobs/?category=writingtranslation&field=all">Writing & Translation</a></h5>
                </div>
				
				<div class="list-group">
                	<h5><a href = "/stulance/get/jobs/?category=adminsupport&field=all">Admin Support</a></h5>
                	<ul>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=adminsupport&field=techsupport" class="l">Technical Support</a></li>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=adminsupport&field=ofcmgmt" class="l">Office Management</a></li>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=adminsupport&field=transcription" class="l">Transcription</a></li>
                	<li><a href="<%=request.getContextPath()%>/get/jobs/?category=adminsupport&field=dataentry" class="l">Data entry</a></li>
                 	</ul>
                </div>
				
				<div class="list-group">
                	<h5><a href = "/stulance/get/jobs/?category=engineeringmanufacturing&field=all">Engineering & Manufacturing</a></h5>
                	<ul>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=engineeringmanufacturing&field=productdesign" class="l">Product Design</a></li>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=engineeringmanufacturing&field=electrical" class="l">Electrical</a></li>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=engineeringmanufacturing&field=cad" class="l">CAD</a></li>
                	<li><a href="<%=request.getContextPath()%>/get/jobs/?category=engineeringmanufacturing&field=mechanical" class="l">Mechanical</a></li>
                 	</ul>
                </div>
				
				<div class="list-group">
                	<h5><a href = "/stulance/get/jobs/?category=household&field=all">Houshold Work</a></h5>
                	<ul>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=household&field=painting" class="l">Painting</a></li>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=household&field=plumbing" class="l">Plumbing</a></li>
                    <li><a href="<%=request.getContextPath()%>/get/jobs/?category=household&field=lawnmowing" class="l">Lawn Mowing</a></li>
                	<li><a href="<%=request.getContextPath()%>/get/jobs/?category=household&field=locksmith" class="l">Locksmith</a></li>
					<li><a href="<%=request.getContextPath()%>/get/jobs/?category=household&field=otherhoushold" class="l">Other Houshold Work</a></li>
                 	</ul>
                </div>
				
            </div>

            <div class="col-md-9">            		 
                <div class="well" style="color:#2c3e50">
                	<font size="5" color: #2c3e50 >Describe Yourself </font>
					<hr>   
                    <div class="row">
                        <div class="col-md-12">
                            <form action="PersonalityResult" method="POST"> 
								<b>At a party do you:</b><br>
								<input type="radio" name="1" value="a" required> Interact with many, including strangers<br>
								<input type="radio" name="1" value="b" required>  Interact with a few, known to you<br>
								<hr>
								<b>Are you more:</b><br>
								<input type="radio" name="2" value="a" required> Realistic than speculative<br>
								<input type="radio" name="2" value="b">  Speculative than realistic<br>
								<hr>
								<b>Is it worse to:</b><br>
								<input type="radio" name="3" value="a" required> Have your "head in the clouds"<br>
								<input type="radio" name="3" value="b">  Be "in a rut"<br>
								<hr>
								<b>Are you more impressed by:</b><br>
								<input type="radio" name="4" value="a" required> Principles<br>
								<input type="radio" name="4" value="b">  Emotions<br>
								<hr>
								<b>Are more drawn toward the:</b><br>
								<input type="radio" name="5" value="a" required> Convincing<br>
								<input type="radio" name="5" value="b">  Touching<br>
								<hr>
								<b>Do you prefer to work:</b><br>
								<input type="radio" name="6" value="a" required> To deadlines<br>
								<input type="radio" name="6" value="b"> Just "whenever"<br>
								<hr>
								<b>Do you tend to choose:</b><br>
								<input type="radio" name="7" value="a" required> Rather carefully<br>
								<input type="radio" name="7" value="b"> Somewhat impulsively<br>
								<hr>
								<b>At parties do you:</b><br>
								<input type="radio" name="8" value="a" required> Stay late, with increasing energy<br>
								<input type="radio" name="8" value="b"> Leave early with decreased energy<br>
								<hr>
								<b>Are you more attracted to:</b><br>
								<input type="radio" name="9" value="a" required> Sensible people<br>
								<input type="radio" name="9" value="b"> Imaginative people <br>
								<hr>
								<b>Are you more interested in:</b><br>
								<input type="radio" name="10" value="a" required> What is actual<br>
								<input type="radio" name="10" value="b"> What is possible<br>
								<hr>
								<b>In judging others are you more swayed by:</b><br>
								<input type="radio" name="11" value="a" required> Laws than circumstances<br>
								<input type="radio" name="11" value="b"> Circumstances than laws<br>
								<hr>
								<b>In approaching others is your inclination to be somewhat:</b><br>
								<input type="radio" name="12" value="a" required> Objective<br>
								<input type="radio" name="12" value="b"> Personal<br>
								<hr>
								<b>Are you more:</b><br>
								<input type="radio" name="13" value="a" required> Punctual<br>
								<input type="radio" name="13" value="b"> Leisurely<br>
								<hr>
								<b>Does it bother you more having things:</b><br>
								<input type="radio" name="14" value="a" required> Incomplete<br>
								<input type="radio" name="14" value="b"> Completed<br>
								<hr>
								<b>In your social groups do you:</b><br>
								<input type="radio" name="15" value="a" required> Keep abreast of other's happenings<br>
								<input type="radio" name="15" value="b"> Get behind on the news<br>
								<hr>
								<b>In doing ordinary things are you more likely to:</b><br>
								<input type="radio" name="16" value="a" required> Do it the usual way<br>
								<input type="radio" name="16" value="b"> Do it your own way<br>
								<hr>
								<b>Writers should:</b><br>
								<input type="radio" name="17" value="a" required> "Say what they mean and mean what they say"<br>
								<input type="radio" name="17" value="b"> Express things more by use of analogy<br>
								<hr>
								<b>Which appeals to you more:</b><br>
								<input type="radio" name="18" value="a" required> Consistency of thought<br>
								<input type="radio" name="18" value="b"> Harmonious human relationships<br>
								<hr>
								<b>Are you more comfortable in making:</b><br>
								<input type="radio" name="19" value="a" required> Logical judgments<br>
								<input type="radio" name="19" value="b"> Value judgments<br>
								<hr>
								<b>Do you want things:</b><br>
								<input type="radio" name="20" value="a" required> Settled and decided<br>
								<input type="radio" name="20" value="b"> Unsettled and undecided<br>
								<hr>
								<b>Would you say you are more:</b><br>
								<input type="radio" name="21" value="a" required> Serious and determined<br>
								<input type="radio" name="21" value="b"> Easy-going<br>
								<hr>
								<div class="text-left">
									<button type="submit" class="btn btn-success btn-lg">Submit</button>
	                       			
			                    </div>
                         	</form>
                        </div>
                    </div>
                    <hr>
                 </div>
			</div>
        </div>
    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

      
    </div>
    <!-- /.container -->

    <!-- jQuery Version 1.11.0 -->
    <script src="http://Stulance.mybluemix.net/forwards/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="http://Stulance.mybluemix.net/forwards/js/bootstrap.min.js"></script>

</body>

</html>
