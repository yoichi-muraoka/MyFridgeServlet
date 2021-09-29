<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>MyFridge</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="">MyFridge</a>
  </div>
</nav>
<div class="container">
  <form action="" method="post">
    <div class="my-5">
      アイテム名: <input type="text" name="name" id="name" class="mr-4">
      賞味期限: <input type="date" name="expDate" id="expDate" class="mr-5">
      <input class="btn btn-primary" type="submit" value="登録">
    </div>
  </form>

  <div class="my-5">
    <a class="btn btn-success active" href="" >全アイテム</a>
    <a class="btn btn-secondary" href="expired.html" >期限切れアイテム</a>
  </div>

  <table class="table table-hover">
    <thead>
      <tr>
        <th>アイテム名</th>
        <th>賞味期限</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${list}" var="item">
      <tr>
        <td><c:out value="${item.name}" /></td>
        <td><fmt:formatDate value="${item.expDate}" pattern="y年MM月dd日(E)" /></td>
        <td><a href="" class="btn btn-danger">削除</a></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
  $("table a").click(function(){
    return confirm("本当に削除しますか？");
  });
});
</script>
</body>
</html>