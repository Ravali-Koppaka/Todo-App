function getTodos(){
	$.ajax({
		url: "http://localhost:8080/TodoApp/TODO/allTodos",
		type: 'GET',
		contentType: 'application/json',
		mimeType: 'application/json',
		
		success : function(data) {
			$.each(data, function(key, val){
				var todo = new Object();
	        	$.each( val , function(property, value){
	        		todo[property] = value;
	        	});
				addToTable(todo);
			})
		},
		error : function(jqXHR) {
			alert("Status:" + jqXHR.status + "\n" +jqXHR.statusText);
		}
	});
}

function addToTable(todo){
	var todoTable = $("#TodoList");
	var div = $("<div id=todo-"+todo["id"]+">");
	var tr = $("<tr/>");
	var td_right = $("<td style='width:1000px;' align='right'/>");
	if(todo["status"] == "COMPLETED"){
		td_right.html('<input type="checkBox" id="checkbox-'+todo["id"]+'" name="complete" value="COMPLETED" disabled=true" checked=true">Completed');
	}else{
		td_right.html('<input type="checkBox" id="checkbox-'+todo["id"]+'" name="complete" value="COMPLETED" onchange="updateTodo('+todo["id"]+')">Completed');
	}
	tr.append(td_right);
	div.append(tr);
	tr = $("<tr/>");
    tr.append("Name : "+ todo["name"]);
    div.append(tr);
    tr = $("<tr/>");
	tr.append("Status : "+ todo["status"]);
	div.append(tr);
	tr = $("<tr/>");
	tr.append("Description : "+ todo["description"]);
	div.append(tr);
	tr = $("<tr/>");
	tr.append("DueDate : "+ todo["dueDate"]);
	div.append(tr);
	tr = $("<tr/>");
	td_right = $("<td style='width:1000px;' align='right'/>");
	td_right.html('<button class="btn btn-primary" type="button" onclick="deleteTodo('+todo["id"]+')">Delete</button><hr>');
	tr.append(td_right);
	div.append(tr);
	todoTable.append(div);
}

function deleteTodo(id){
	$.ajax({
		url:  "http://localhost:8080/TodoApp/TODO/"+id,
		type: 'DELETE',
		
		success : function(data){
			$("#todo-"+id).remove();
		},
		error : function(jqXHR){
			alert("Status:" + jqXHR.status + "\n" +jqXHR.statusText);
		}
	});
}

function updateTodo(id){
	$.ajax({
		url:  "http://localhost:8080/TodoApp/TODO/"+id,
		type: 'PUT',
		
		success : function(data){
			$("#checkbox-"+id).prop("disabled", true);
			
		},
		error : function(jqXHR){
			alert("Status:" + jqXHR.status + "\n" +jqXHR.statusText);
		}
	}); 
	
}

function addTodos(){
	var todo = new Object();
	todo.name = $("#name").val();
	todo.description = $("#description").val();
	todo.dueDate = $("#dueDate").val();
	
	$.ajax({
        url:  "http://localhost:8080/TodoApp/TODO",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(todo),
        contentType: 'application/json',
        mimeType: 'application/json',
 
        success: function (data) {
        	var todo = new Object();
        	$.each( data , function( key , val ){
        		todo[key] = val;
        	});
        	addToTable(todo);
        	$("#name").val("");
        	$("#description").val("");
        	$("#dueDate").val("");
        },
        error:function(jqXHR) {
            alert("Status:" + jqXHR.status + "\n" +jqXHR.statusText);
        }
    });
}