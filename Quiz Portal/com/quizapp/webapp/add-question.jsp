<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html><head><title>Add Question</title></head>
<body>
<div class="container">
  <h3>Add Question</h3>
  <form method="post" action="${pageContext.request.contextPath}/admin/question/add">
    <div><textarea name="text" class="form-control" placeholder="Question" required></textarea></div>
    <div><input name="optionA" placeholder="Option A" class="form-control" required/></div>
    <div><input name="optionB" placeholder="Option B" class="form-control" required/></div>
    <div><input name="optionC" placeholder="Option C" class="form-control" required/></div>
    <div><input name="optionD" placeholder="Option D" class="form-control" required/></div>
    <div>
      <label>Correct Option</label>
      <select name="correctOption" class="form-control">
        <option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option>
      </select>
    </div>
    <button class="btn btn-success">Save</button>
  </form>
</div>
</body></html>
