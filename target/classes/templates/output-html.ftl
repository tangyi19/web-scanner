<!DOCTYPE html>
<html>
<head>
    <title>webScanner Report</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        body {
            margin: 0;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        }

        a {
            text-decoration: none;
        }

        .navbar {
            background-color: #0b77df;
            position: fixed;
            top: 0;
            width: 100%;
            height: 60px;
        }

        #logo {
            margin: 20px 0px 20px 20px;
        }

        .table {
            margin-top: 70px;
            padding: 10px 20px 10px 20px;
        }

        #table {
            border-collapse: collapse;
            width: 100%;
            table-layout: fixed;
            word-break: break-all
        }

        #table td, #table th {
            border: 1px solid #ddd;
            padding: 4px;
        }

        #table tr.table-item:hover {
            background-color: #ddd;
        }

        #table tr.table-item {
            cursor: pointer;
        }

        #table th {
            padding-top: 8px;
            padding-bottom: 8px;
            border: 1px solid #eee;
            background-color: #ddd;
            color: black;
            text-align: left;
        }

        .detail-item {
            margin: 5px;
        }

        [class^="table-detail-"] {
            display: none;
        }

        pre {
            margin: 1px;
        }

        .footer {
            padding: 10px 20px 10px 20px;
        }

        .feedback {
            font-size: 80%;
        }

        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        }

        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px 20px 70px 20px;
            border: 1px solid #888;
            width: 50%;
        }

        .button {
            border: none;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .feedback-submit-btn {
            background-color: #4CAF50;
            float: right;
        }

        .feedback-cancel-btn {
            background-color: darkgray;
            float: right;
        }

        #feedback-comment {
            width: 100%;
            border: 1px solid black;
        }

    </style>
    <link href="https://cdn.bootcss.com/prism/9000.0.1/themes/prism.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/prism/9000.0.1/prism.min.js" data-manual></script>
    <script src="https://cdn.bootcss.com/prism/9000.0.1/components/prism-http.min.js"></script>
    <script src="https://cdn.bootcss.com/prism/9000.0.1/components/prism-javascript.min.js"></script>
    <style>
        pre[class*="language-"] {
            padding: .5em;
            margin: .5em 0;
            overflow: auto;
            max-height: 300px;
        }

        pre[class*="language-"] {
            background: white;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1 style="color: #eee;">Web Scanner</h1>
</div>
<div class="table">
    <table id="table">
    <tr><th colspan="1">#</th>
        <th colspan="3">Plugin / VulnType</th>
        <th colspan="6">Target</th>
    </tr>
    <#list vList as node>
        <tr>
            <td colspan="1">${node.id}</td>
            <td colspan="3">${node.vulType}</td>
            <td colspan="6">${node.url}</td>
        </tr>
    </#list>
</div>

