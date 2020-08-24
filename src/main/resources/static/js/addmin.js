
$(document).ready(function(){
	console.log('시작 = ',$('input:radio[name="radioSale"]:checked').val());
    // 라디오버튼 클릭시 이벤트 발생
    $("input:radio[name=radioSale]").click(function(){
        if($("input:radio[name=radioSale]:checked").val() == 'true'){
            $(".radioSale").attr("disabled",false);
            // radio 버튼의 value 값이 1이라면 활성화
        	console.log($('input:radio[name="radioSale"]:checked').val());
        }else if($("input:radio[name=radioSale]:checked").val() == 'false'){
              $(".radioSale").attr("disabled",true);
          	console.log($('input:radio[name="radioSale"]:checked').val());
            // radio 버튼의 value 값이 0이라면 비활성화
        }
    });
});



	var sel_file;

	$(document).ready(function() {
		$("#input_img").on("change", handleImgFileSelect);
	});

	function handleImgFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}

			sel_file = f;

			var reader = new FileReader();
			reader.onload = function(e) {
				$("#img").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}


let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.save();
			});
			$("#btn-update").on("click", ()=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.update();
			});
		},


		save: function(){
			// alert('user의 save함수 호출됨');
			let data = {
					title: $("#title").val(),
					thumb: $("#thumb").val(),
					price: $("#price").val(),
					disc: $("#disc").val(),
					discounted: $("#discounted").val(),
					content: $("#content").val(),
					bgImg: $("#input_img").val(),
					radioSale : $('input:radio[name="radioSale"]:checked').val(),
					radioAd : $('input:radio[name="radioAd"]:checked').val(),
					radioParentTypeId : $('input:radio[name="radioParentTypeId"]:checked').val(),
					radioBest : $('input:radio[name="radioBest"]:checked').val(),
					radioNew : $('input:radio[name="radioNew"]:checked').val(),


			};
			console.log(data);


			// console.log(data);

			// ajax호출시 default가 비동기 호출
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
			$.ajax({
				type: "POST",
				url: "/crud",
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",// body데이터가 어떤
																// 타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				alert('성공');
// if(resp.status === 500){
// alert("회원가입에 실패하였습니다.");
// }else{
// alert("회원가입이 완료되었습니다.");
	//				location.href = "/list";
// }

			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},

		update: function(){
			// alert('user의 save함수 호출됨');
			let data = {
					id: $("#id").val(),
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val()
			};

			$.ajax({
				type: "PUT",
				url: "/user",
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",// body데이터가 어떤
																// 타입인지(MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				alert("회원수정이 완료되었습니다.");
				// console.log(resp);
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},
}

index.init();