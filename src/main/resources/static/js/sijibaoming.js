$(document).ready(function(){

				$.get('json/school.json',function(mydata){
					var sel_1=document.getElementById("se_1")
					var sel_2=document.getElementById("se_2")
					var store=  new Array()
					for(var i=0;i<mydata.campus.length;i++){
						store.push(mydata.campus[i][0])
					}
					for(var i=0;i<store.length;i++){
						var opt=document.createElement("option")
						opt.innerHTML=store[i]
						sel_1.appendChild(opt)
					}
					//第二个select，初始
					store.splice(0,store.length)  //清空数组
					var index_1=sel_1.selectedIndex
					var value_1=sel_1.options[index_1].text  //第一个select中的显示的option的值
					for(var i=1;i<mydata.campus[index_1].length;i++){
						store.push(mydata.campus[index_1][i])
					}
					for(var i=0;i<store.length;i++){
						var opt_2=document.createElement("option")
						opt_2.innerHTML=store[i]
						sel_2.appendChild(opt_2)
					}
					store.splice(0,store.length)
					
					//前面2个option的值已经有了，现在需要设置第三个
//					var index_3=sel_3.selectedIndex
//					var value_3=sel_3.options[index_3].text
//					var opt=document.getElementById("detailed")
//					opt.value=value_1+"-"+value_2+"-"+value_3+"-"

				})
		})
function after_change(){
			$.get('json/school.json',function(mydata){
				//删除selec的已存孩子，嘿嘿
				while(document.getElementById("se_2").hasChildNodes()){
						document.getElementById("se_2").removeChild(document.getElementById("se_2").firstChild)
					}
				var sel_1=document.getElementById("se_1")
				var sel_2=document.getElementById("se_2")
				var index_1=sel_1.selectedIndex
				var value_1=sel_1.options[index_1].text
				var store=  new Array()
				for(var i=1;i<mydata.campus[index_1].length;i++){
						store.push(mydata.campus[index_1][i])
					}
				for(var i=0;i<store.length;i++){
						var opt_2=document.createElement("option")
						opt_2.innerHTML=store[i]
						sel_2.appendChild(opt_2)
					}
				store.splice(0,store.length)
				
		})
	}
			
		