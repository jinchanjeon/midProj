<!-- //eventInsert.jsp -->

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
    <title>Submit Event</title>

<style>
.ck.ck-editor {
    max-width: 500px;
}
.ck-editor__editable {
    min-height: 300px;
}


   </style>
</head>
<body>


<%@include file="../../includes/top.jsp" %>

 <section class="meetings-page" id="meetings">
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <div class="row">
                <div class="col-lg-12">
                  <div class="filters">
                    <ul>
                      <li><a href="/MiddleProj/eventList">EVENT</a></li>
                    </ul>
                  </div>
                </div>
     <form action="/MiddleProj/eventInsert" method="post" enctype="multipart/form-data">
         <label for="title"><font color="white">Title:</label>
         <input type="text" id="title" name="title">
         <label for="title"><font color="white">startDate</label>
         <input type="date" id="startDate" name="startDate">
         <label for="title"><font color="white">EndDate</label>
 		 <input type="date" id="endDate" name="endDate">
 		 

 		 
         <textarea id="content" name="content"></textarea>
 
         <button type="submit">Submit</button>
</form>
     <script>
         ClassicEditor
             .create(document.querySelector('#content'))
             .catch(error => {
                 console.error(error);
             });
         



     </script>
    <%@ include file = "../../includes/bottom.jsp"%>
</body>
