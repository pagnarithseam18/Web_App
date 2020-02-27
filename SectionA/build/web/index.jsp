<%-- 
    Document   : index
    Created on : Feb 20, 2020, 1:54:35 PM
    Author     : Pagnarith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .tablecontainer
            {
                position: absolute;
                top: 300px;
                left: 300px;
            }
            table, th, td
            {
                border: 1px solid black;
            }
            </style>
        <script>
            function changeFunction(value)
            {
                const main = document.getElementById("main");
                document.getElementById("tablecontainer").innerHTML = "";
                main.innerHTML = "";
                if(value.toString() == "insert")
                {
                    const form = document.createElement("form");
                    const inputId = document.createElement("input");
                    const inputName = document.createElement("input");
                    const inputSubmit = document.createElement("input");
                    const inputHidden = document.createElement("input");
                    inputId.setAttribute("type", "text");
                    inputId.setAttribute("name", "id");
                    form.appendChild(inputId);
                    inputName.setAttribute("type", "text");
                    inputName.setAttribute("name", "firstName");
                    form.appendChild(inputName);
                    inputSubmit.setAttribute("type", "submit");
                    inputSubmit.setAttribute("value", "Insert Record");
                    form.appendChild(inputSubmit);
                    inputHidden.setAttribute("type", "hidden");
                    inputHidden.setAttribute("name", "option");
                    inputHidden.setAttribute("value", value);
                    form.appendChild(inputHidden);
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "DoOperations");
                    main.appendChild(form);
                }
                else if(value == "view")
                {
                    const form = document.createElement("form");
                    const inputId = document.createElement("input");
                    const inputSubmit = document.createElement("button");
                    const inputHidden = document.createElement("input");
                    inputId.setAttribute("type", "text");
                    inputId.setAttribute("name", "id");
                    inputId.setAttribute("id", "id");
                    inputSubmit.setAttribute("type", "button");
                    inputSubmit.setAttribute("onclick", "callServletWithAjax()");
                    inputSubmit.innerHTML = "View Record";
                    inputHidden.setAttribute("type", "hidden");
                    inputHidden.setAttribute("name", "option");
                    inputHidden.setAttribute("id", "option");
                    inputHidden.setAttribute("value", value);
                    form.appendChild(inputHidden);
                    form.appendChild(inputId);
                    form.appendChild(inputSubmit);
                    form.setAttribute("method", "POST");
                    main.appendChild(form);
                }
                else if(value == "delete"){
                    const form = document.createElement("form");
                    const inputId = document.createElement("input");
                    const inputSubmit = document.createElement("input");
                    const inputHidden = document.createElement("input");
                    inputId.setAttribute("type", "text");
                    inputId.setAttribute("name", "id");
                    inputId.setAttribute("id", "id");
                    form.appendChild(inputId);
                    inputSubmit.setAttribute("type", "submit");
                    inputSubmit.setAttribute("value", "Submit");
                    form.appendChild(inputSubmit);
                    inputHidden.setAttribute("type", "hidden");
                    inputHidden.setAttribute("id", "option");
                    inputHidden.setAttribute("name", "option");
                    inputHidden.setAttribute("value", value);
                    form.appendChild(inputHidden);
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "DoOperations");
                    main.appendChild(form);
                    
                }
                else if(value == "viewall")
                {
                    const form = document.createElement("form");
                    const inputSubmit = document.createElement("button");
                    const inputHidden = document.createElement("input");
                    inputSubmit.setAttribute("type", "button");
                    inputSubmit.setAttribute("onclick", "callServletWithAjax()");
                    inputSubmit.innerHTML = "View All Records";
                    inputHidden.setAttribute("type", "hidden");
                    inputHidden.setAttribute("name", "option");
                    inputHidden.setAttribute("id", "option");
                    inputHidden.setAttribute("value", value);
                    form.appendChild(inputHidden);
                    form.appendChild(inputSubmit);
                    form.setAttribute("method", "POST");
                    main.appendChild(form);
                }
            }
            
            function callServletWithAjax()
            {
                var xmlhttp;
                if(window.XMLHttpRequest)
                    xmlhttp = new XMLHttpRequest();
                else
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                
                xmlhttp.onreadystatechange = function()
                {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        document.getElementById("tablecontainer").innerHTML = xmlhttp.responseText;  
                    }
                };
                if(document.getElementById("options").value == "view")
                    var params = "id=" + document.getElementById("id").value + "&option=" + document.getElementById("option").value;
                else if(document.getElementById("options").value == "viewall")
                    var params = "option=" + document.getElementById("option").value;
                xmlhttp.open("POST", "/SectionA/DoOperations", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xmlhttp.send(params);
            }
            
        </script>
    </head>
    <body>
        <select id="options" name="options" onChange="changeFunction(this.value)">
            <option value="">...</option>
            <option value="insert">Insert Record</option>
            <option value="view">View Record</option>
            <option value="update">Update Record</option>
            <option value="delete">Delete Record</option>
            <option value="viewall">View All Records</option>
        </select>
        <div id="main"></div>
        <div class="tablecontainer" id="tablecontainer">J</div>
    </body>
</html>
