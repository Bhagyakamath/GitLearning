<%@ page import="java.util.List, model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<%
    // Get current question(s) and pagination info from servlet
    List<Question> questions = (List<Question>) request.getAttribute("questions");
    int currentPage = (Integer) request.getAttribute("currentPage");
    int totalPages = (Integer) request.getAttribute("totalPages");
    int quizId = (Integer) request.getAttribute("quizId");
%>

<div class="container mt-5 pt-4">
    <h2 class="mb-4">Quiz</h2>

    <form action="SubmitQuizServlet" method="post">
        <input type="hidden" name="quizId" value="<%= quizId %>">

        <%-- Display the current question(s) --%>
        <% if (questions != null && !questions.isEmpty()) {
               for (Question q : questions) { %>
            <div class="card mb-4 shadow-sm p-3">
                <p><strong>Q<%= currentPage %>: <%= q.getText() %></strong></p>

                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%= q.getId() %>" value="A" required>
                    <label class="form-check-label"><%= q.getOptionA() %></label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%= q.getId() %>" value="B">
                    <label class="form-check-label"><%= q.getOptionB() %></label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%= q.getId() %>" value="C">
                    <label class="form-check-label"><%= q.getOptionC() %></label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="q<%= q.getId() %>" value="D">
                    <label class="form-check-label"><%= q.getOptionD() %></label>
                </div>
            </div>
        <% } } else { %>
            <p class="text-danger">No questions available for this quiz.</p>
        <% } %>

        <%-- Pagination buttons --%>
        <div class="d-flex justify-content-between mb-5">
            <% if(currentPage > 1) { %>
                <a class="btn btn-secondary"
                   href="StartQuizServlet?quizId=<%= quizId %>&page=<%= currentPage-1 %>">Previous</a>
            <% } else { %>
                <div></div>
            <% } %>

            <% if(currentPage < totalPages) { %>
                <a class="btn btn-primary"
                   href="StartQuizServlet?quizId=<%= quizId %>&page=<%= currentPage+1 %>">Next</a>
            <% } else { %>
                <input type="submit" class="btn btn-success" value="Submit">
            <% } %>
        </div>

        <%-- Optional: Show current page info --%>
        <p class="text-end text-muted">Question <%= currentPage %> of <%= totalPages %></p>
    </form>
</div>
