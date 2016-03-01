<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Upload</title>
</head>
<body>
    <p>上传文件，字符流保存</p>
    <form action="upload.action" method="post" enctype="multipart/form-data">
        <table width="405" height="88" style="width: 376px; height: 89px;">
            <tr>
                <td>上传文件：</td>
                <td><input type="file" multiple name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
                <td><input type="reset"></td>
            </tr>
        </table>
    </form>
    
    <p>上传文件，缓存保存</p>
    <form action="file/upload2.action" method="post" enctype="multipart/form-data">
        <table width="405" height="88" style="width: 376px; height: 89px;">
            <tr>
                <td>上传文件：</td>
                <td><input type="file" multiple name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
                <td><input type="reset"></td>
            </tr>
        </table>
    </form>
    
    <p>上传文件，缓存保存，支持html5 multiple</p>
    <form action="file/upload3.action" method="post" enctype="multipart/form-data">
        <table width="405" height="88" style="width: 376px; height: 89px;">
            <tr>
                <td>上传文件：</td>
                <td><input type="file" multiple name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
                <td><input type="reset"></td>
            </tr>
        </table>
    </form>
    
    <a target="_blank" href="file/download">下载文件</a>
    <a target="_blank" href="file/links">在线预览</a>
</body>
</html>