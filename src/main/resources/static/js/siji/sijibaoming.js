$(document).ready(function(){

				$.get('json/data.json',function(mydata){
					var sel_1=document.getElementById("se_1")
					var sel_2=document.getElementById("se_2")
					var sel_3=document.getElementById("se_3")
					var store=  new Array()
					for(var i=0;i<mydata.length;i++){
						if(mydata[i]["level"]==1){
							store.push(mydata[i]["name"])
						}
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

					for(var i=0;i<mydata.length;i++){
						if(mydata[i]["name"]==value_1){
							for(var j=0;j<mydata.length;j++){
								if((mydata[j]["sheng"]==mydata[i]["sheng"])&&(mydata[j]["level"]==2)){
									var opt_2=document.createElement("option")
									opt_2.innerHTML=mydata[j]["name"]
									sel_2.appendChild(opt_2)
								}
							}
							break
						}
					}
					//第三个select,初始
					var index_2=sel_2.selectedIndex
					var value_2=sel_2.options[index_2].text
					for(var i=0;i<mydata.length;i++){
						if(mydata[i]["name"]==value_2&&mydata[i]["level"]==2){
							for(var j=0;j<mydata.length;j++){
								if((mydata[j]["sheng"]==mydata[i]["sheng"])&&(mydata[j]["di"]==mydata[i]["di"])&&(mydata[j]["level"]==3)){
									var opt_3=document.createElement("option")
									opt_3.innerHTML=mydata[j]["name"]
									sel_3.appendChild(opt_3)
								}
							}
							break
						}
					}
					//前面2个option的值已经有了，现在需要设置第三个
					var index_3=sel_3.selectedIndex
					var value_3=sel_3.options[index_3].text
					var opt=document.getElementById("detailed")
					opt.value=value_1+"-"+value_2+"-"+value_3+"-"

//					//下面是对性别的select进行的操作
//					var opt_0=document.getElementById("ipt3")
//					var sel_0=document.getElementById("se_0")
//					var index_0=sel_0.selectedIndex
//					var value_0=sel_0.options[index_0].text
//					opt_0.value=value_0
//                 后面发现性别不重要就把他舍弃掉了
//



				})
			})

		//这个函数是显示所在地区input的文本
		//这个函数要放到区县的select里面去调用，因为我在设计的时候，其他函数里面没有关于它的响应
		function show_value(){
			var sel_1=document.getElementById("se_1")
			var sel_2=document.getElementById("se_2")
			var sel_3=document.getElementById("se_3")

			var index_3=sel_3.selectedIndex
			var value_3=sel_3.options[index_3].text
			var index_2=sel_2.selectedIndex
			var value_2=sel_2.options[index_2].text
			var index_1=sel_1.selectedIndex
			var value_1=sel_1.options[index_1].text

			var opt=document.getElementById("detailed")
			opt.value=value_1+"-"+value_2+"-"+value_3+"-"

		}

//这个不需要了
//		//对于上面的一个隐藏input进行操作，对于性别的
//		function select_forms() {
//			var opt_0=document.getElementById("ipt3")
//			var sel_0=document.getElementById("se_0")
//			var index_0=sel_0.selectedIndex
//			var value_0=sel_0.options[index_0].text
//			opt_0.value=value_0
//      }

		function after_change(){
			$.get('json/data.json',function(mydata){
				//删除selec的已存孩子，嘿嘿
				while(document.getElementById("se_2").hasChildNodes()){
						document.getElementById("se_2").removeChild(document.getElementById("se_2").firstChild)
					}

			    while(document.getElementById("se_3").hasChildNodes()){//这里的2个不能够进行或合并，因为数值不等
						document.getElementById("se_3").removeChild(document.getElementById("se_3").firstChild)
					}
				    var index_1=document.getElementById("se_1").selectedIndex
				    var value_1=document.getElementById("se_1").options[index_1].text
					for(var i=0;i<mydata.length;i++){
						if(mydata[i]["name"]==value_1&&mydata[i]["level"]==1){
							for(var j=0;j<mydata.length;j++){
								if(mydata[j]["sheng"]==mydata[i]["sheng"]&&mydata[j]["level"]==2){
									var opt_2=document.createElement("option")
									opt_2.innerHTML=mydata[j]["name"]
									document.getElementById("se_2").appendChild(opt_2)

									}
								}
							break
						}
					}
					var index_2=document.getElementById("se_2").selectedIndex;
			        var value_2=document.getElementById("se_2").options[index_2].text
			        for(var i=0;i<mydata.length;i++){
			    	    if(mydata[i]["name"]==value_2&&mydata[i]["level"]==2){
				    		for(var j=0;j<mydata.length;j++){
				    			if(mydata[j]["sheng"]==mydata[i]["sheng"]&&mydata[j]["di"]==mydata[i]["di"]&&mydata[j]["level"]==3){
				    				var opt_3=document.createElement("option")
									opt_3.innerHTML=mydata[j]["name"]
									document.getElementById('se_3').appendChild(opt_3)
				    			}
				    		}
			    		break
			    	    }
			        }
		})
			show_value()

		}
		function after_change_2(){
			$.get("json/data.json",function(mydata){
				while(document.getElementById("se_3").hasChildNodes()){
					 document.getElementById("se_3").removeChild(document.getElementById("se_3").firstChild)
					}
				var index_2=document.getElementById("se_2").selectedIndex;
			    var value_2=document.getElementById("se_2").options[index_2].text
			    for(var i=0;i<mydata.length;i++){
			    	if(mydata[i]["name"]==value_2&&mydata[i]["level"]==2){
			    		for(var j=0;j<mydata.length;j++){
			    			if(mydata[j]["sheng"]==mydata[i]["sheng"]&&mydata[j]["di"]==mydata[i]["di"]&&mydata[j]["level"]==3){
			    				var opt_3=document.createElement("option")
								opt_3.innerHTML=mydata[j]["name"]
								document.getElementById('se_3').appendChild(opt_3)
			    			}
			    		}
			    		break
			    	}
			    }
			})
			show_value() //在所在地区显示值的
		}
