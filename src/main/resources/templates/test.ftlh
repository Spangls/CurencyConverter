<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>hasClass demo</title>
    <style>
        p {
            margin: 8px;
            font-size: 16px;
        }
        .selected {
            color: red;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>

<p>This paragraph is black and is the first paragraph.</p>
<p class="selected-cpu">This paragraph is red and is the second paragraph.</p>
<div id="result1">First paragraph has selected class: </div>
<div id="result2">Second paragraph has selected class: </div>
<div id="result3">At least one paragraph has selected class: </div>

<script>
    $( "#result3" ).append( $( "p" ).hasClass( "selected" ).toString() ) ;
    $( "#result3" ).append( $( "p" ).hasClass( "cpu" ).toString() ) ;
    $( "#result3" ).append( $( "p" ).hasClass( "selected-cpu" ).toString() ) ;
</script>

</body>
</html>