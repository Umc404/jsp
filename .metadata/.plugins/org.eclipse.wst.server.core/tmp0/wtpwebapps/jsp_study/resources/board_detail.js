/**
 * 
 */
console.log("board_detail.js in.");
console.log(bnoVal);

// 등록 post / button : cmtAddBtn
// 등록 버튼을 누르면 작성자와 댓글 내용의 값을 객체로 생성 => controller로 전송
document.getElementById('cmtAddBtn').addEventListener('click',()=> {
	let cmtText = document.getElementById("cmtText").value;
	let cmtWriter = document.getElementById("cmtWriter").value;
	if(cmtText ==null || cmtText == ''){
		alert("댓글을 입력해주세요.");
		return false;
	}
	// 댓글 객체 생성	
	let cmtData = {
		bno : bnoVal,
		writer : cmtWriter,
		content : cmtText
	}
	// 댓글을 비동기로 전송
	postCommentToServer(cmtData).then(result => {
		console.log(result);		// isOk
		if(result == '1'){
			alert("댓글 등록 성공.");
		} else {
			alert("댓글 등록 실패.");
		}
		
		// 댓글 입력 후 다른 댓글 입력할 수 있도록 공백처리
		document.getElementById("cmtText").value = "";
		document.getElementById("cmtWriter").value = "";
		
		// 댓글 출력
		printList(bnoVal);
	});
});

// result.length : 댓글 유무 확인하여 없을 경우 comment가 없습니다. 출력
function printList(bnoVal) {
	getCommentListFromServer(bnoVal).then(result =>{
		console.log(result);
		if(result.length > 0) {
			printCommentList(result);
		} else {
			let div = document.getElementById('commentLine');
			div.innerHTML = `<div>comment 가 없습니다.</div>`;
		}
	})
}

// 값이 있을 경우 해당함수 호출하여 실행
function printCommentList(result) {
	let div = document.getElementById('commentLine');
	div.innerText='';	// 기존에 값이 있다면 구조 지우기
	for(let i=0; i<result.length; i++) {
		let html = `<div>`;
		html += `<div>${result[i].cno}, ${result[i].bno}, ${result[i].writer}, ${result[i].regdate}</div>`;
		html += `<div>`;
		html += `<button type="button" data-cno="${result[i].cno}" class="cmtModBtn">수정</button>`;
		html += `<button type="button" data-cno="${result[i].cno}" class="cmtDelBtn">삭제</button><br>`;
		html += `<input type="text" class="cmtText" id="${result[i].cno}" value="${result[i].content}">`;
		html += `</div></div>`;
		html += `</div>`;
		div.innerHTML += html;
	}
}

// 화면에서 데이터를 만들어서 보내는 방법 = post
// 데이터를 보낼 때 method=post, headers(content-Type), body 를 작성해서 전송
// 서버에서 데이터를 주는 방법 = get
// Content-Type : application/json; charset=utf-8

async function postCommentToServer(cmtData) {
	try {
		console.log(cmtData);
		const url = "/cmt/post";
		const config = {
			method: 'post',
			headers: {
				'Content-Type':'application/json; charset=utf-8'
			},
			body: JSON.stringify(cmtData)
		};
		const resp = await fetch(url, config);
		const result = await resp.text(); // isOk 값을 text로 리턴
		return result;
	} catch(error) {
		console.log(error);
	}
};

// list 가져오기 : 내 게시글에 달린 댓글만 가져오기 => get (생략 가능)
async function getCommentListFromServer(bno) {
	try {
		const resp = await fetch("/cmt/list?bno="+ bno);		// 백틱 `` 으로도 표현 가능
		const result = await resp.json();		// 댓글 리스트 [{...}, {...}, {...}]
		return result;
	} catch(error) {
		console.log(error);
	}
};

// update comment
async function updateCommentToServer(cmtData) {
	// 수정 : cno, content 객체를 보내서 isOk return => post
	try{
		const url = "/cmt/modify";
		const config = {
			method : 'post',
			headers : {
				'Content-Type':'application/json; charset=utf-8'
			},
			body: JSON.stringify(cmtData)
		}
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
	} catch(error) {
		console.log(error);
	}
}

// delete comment
async function removeCommentToServer(cnoVal) {
	try {
			const resp = await fetch("/cmt/remove?cno="+cnoVal);
			const result = await resp.text();
			return result;
		} catch(error){
			console.log(error);
		}
}
	
document.addEventListener('click',(e)=> {
	console.log(e.target);
	console.log(e.target.dataset.cno);
	
	// 수정		
		if(e.target.classList.contains('cmtModBtn')) {
			// 수정에 대한 처리
			let cnoVal = e.target.dataset.cno;
			// cno 값을 id로 사용할 경우
			let cmtText = document.getElementById(cnoVal).value;
			console.log(cmtText);
			
			let cmtData = {
				cno : cnoVal,
				content : cmtText,
			}

			updateCommentToServer(cmtData).then(result => {
				console.log(result);
				if(result == '1'){
					alert("댓글 수정 성공.");
				} else {
					alert("댓글 수정 실패.");
				}
				printList(bnoVal);
			});
		}
	
	// 내 타겟을 기준으로 가장 가까운 div를 찾기 closest('div')
	//	let div = e.target.closest('div');
	//	console.log(div);
	//	let cmtText2 = div.querySelector('.cmtText').value;
	//	console.log(cmtText2);
	
	// 삭제
	if(e.target.classList.contains('cmtDelBtn')){
		// 삭제에 대한 처리
		let cnoVal = e.target.dataset.cno;
		// 삭제 비동기함수 호출
		removeCommentToServer(cnoVal).then(result => {
			// 삭제 후 출력 메서드 호출
			if(result == '1') {
				alert("댓글 삭제 성공.");
			} else {
				alert("댓글 삭제 실패.");
			}
			printList(bnoVal);
		})
	}
})




