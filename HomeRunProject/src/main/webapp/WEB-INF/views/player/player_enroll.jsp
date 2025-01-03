<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선수 등록용 화면</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>선수등록</h2>
            <br>

            <form action="savePlayer.player" method="post">
                <div class="form-group" id="player-enroll-form">
                    <label for=""> 선수 등급 : </label> &nbsp;&nbsp;
                    <input type="radio" id="pro" value="1" name="grade">
                    <label for="Male">프로</label> &nbsp;&nbsp;
                    <input type="radio" id="amateur" value="2" name="grade">
                    <label for="Female">아마추어</label> &nbsp;&nbsp;
                    <input type="radio" id="normal" value="3" name="grade" checked>
                    <label for="Female">일반</label> &nbsp;&nbsp;
                    <br><br>
            
                    포지션:
                    <select name="playerPosition">
                        <option value="1">감독</option>
                        <option value="2">투수</option>
                        <option value="3">포수</option>
                        <option value="4">내야수</option>
                        <option value="5">외야수</option>
                    </select>
                    <br><br>
            
                    <fieldset>
                        <legend>자기소개 작성</legend>
                        <textarea id="player-intro" name="playerIntro" cols="53" rows="34" style="resize:none;" placeholder="1000자 정도로 시합성적 및 자신소개글을 입력하세요." maxlength="1000"></textarea>
                        <br>
                        <span id="count">0</span> / 1000
                    </fieldset>
                    <br>

                    <label for="player-salary">희망 연봉 : </label>
                    <input type="number" min="1000000" max="1000000000" step="1000000" name="playerSalary" value="1000000">                    
                    <br><br>
                </div>
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary disabled" id="enroll-btn">제출</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>
    </div>
    
    <div>
    <h1>여기 등록된 전체 선수 열람</h1>
    </div>
    
    
    
    
    
    
    
    
    

    <script>
        $(function(){
            $('#player-intro').keydown(function(){
                $('#count').text(this.value.length);
            });
        });

    </script>


</body>
</html>