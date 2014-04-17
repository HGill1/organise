var setCounter = 0;

function init() {
	$('input:button').button();
	$('#submit').button();
	
	//console.log($("div").data("options").name);

		$(document).mousemove(function(event){
			checkSessionTimeOut();
		});
	
	//$("#control_8").multiSelect({ selectAllText: 'Pick &lsquo;em all!' });
		
	
	$('#toggleAddForm').live("click", function (event) {
		checkSessionTimeOut();
	});
	
	$('#topicSave').live("click", function(event){
		
		
		$('#subTopicsName').val(subtopicArray);
		//subtopicArray = [];
	});
	
	$('a:not(.selectBox, .selectBox-dropdown-menu a)').click(function() {
	
	$("#allSourcesList").one('click', 'a', function() {
		resetAllVariable();	
		//event.preventDefault();
		$('.new-item').removeAttr('style');
		$('.left-nav-dropdown').children('li').removeAttr('style');
		//event.stopPropagation();
		if(jQuery(this).attr('id') == 'multiSourceFilterId'){
			//$(this).on(event);
			$('#filterCatId').val(0);
			jQuery('[data-control=leftNavFolders] .green').removeClass('green');
			jQuery(this).closest("li").addClass("green");
			$('#multiSourceFilter').val(true);
			var userId = $('#hiddenUserId').val();
			$('#editShowHideIcon').remove();
			jQuery('[data-control=editLeftNav]').append("<i id='editShowHideIcon' class='icon-edit-box'></i>");
			updateFeedSources(userId); 
			multiSourceFilterFeeds(userId, 0,false);
		}
			
		
		if(jQuery(this).attr('id').substr(0,6) == 'source'){
			  //japp.campaign.hideReply();
			 var catId = jQuery(this).attr('id').substr(6);
			 $('#filterCatId').val(catId);
			 $("#feedSourceId").selectBox('value', catId+'-'+jQuery(this).data("topicColour"));
			 jQuery(this).parent('li').attr('style', 'text-shadow:#fff 0px 1px 0, #000 0 -1px 0;BACKGROUND-COLOR: #CDDAED');
			 $('#filterStatusId').val(0);
			 $('#filterUserId').val(0);
			 filterFeeds(catId,0,0,false,0,false);
			 clickedOnce = false;
		}
		
		if(jQuery(this).attr('id').substr(0,8) == 'subtopic'){
			var subTopicId = jQuery(this).attr('id').substr(8);
			deleteSubTopic(subTopicId);
		}		
	});
	
	$("#allFollowingList").one('click', 'a', function() {
		resetAllVariable();
		if(jQuery(this).attr('id') == 'followingCat'){
			var followingCatId = jQuery(this).children("input").attr('value');
			var followerId = $('#hiddenUserId').val();
			$('#followingByCat').val(true);
			$('#followCatId').val(followingCatId);
			followedTasksInCat(followingCatId,followerId,0,false);
		}
	});
	
	$("#recentlyViewedTh").one('click', 'a', function() {
		resetAllVariable();
		var catId = jQuery(this).children("input").attr('value');
		var viewerId = $('#hiddenUserId').val();
		recentlyViewedThread(viewerId,catId,0,false);
	});
	
	$("#onlineUsersContainer").on('click', 'a', function() {
		showUserTasks(jQuery(this));		
	});
	
	$("#offlineUsersContainer").one('click', 'a', function() {
		showUserTasks(jQuery(this));		
	});
	
});
	
	  $("#selectTasksType").selectBox().change(
		        function() {
		        	$('#myAddedTasksClicked').val(false);
		    		$('#urgentClicked').val(false);
		    		$('#displayAllUsersTasks').val(false);
		    		resetAllVariable();
		    		selectedTaskType = $(this).val();
		    		selectedOrder = $("#orderBy").val();
		    		pageStart=1;
		    		loadingImage();
		        	if(selectedTaskType == 'myTask'){
		        		/*var userId = $('#hiddenUserId').val();
		    			$('#filterUserId').val(userId);
		    			$('#filterStatusId').val(0);
		         		$('#filterCatId').val(0);
		    			filterFeeds(0,userId,0,false,0,false,null,selectedOrder);*/
		        		tId = jQuery('#filterCatId').val();
		        		var userId = $('#hiddenUserId').val();
		    			$('#displayAllUsersTasks').val(true);
		    			displayAllMyTasks(userId,0,false,0,tId);
		    		}
		    		if(selectedTaskType == 'myAddedTasks'){
		    			tId = jQuery('#filterCatId').val();
		    			var userId = $('#hiddenUserId').val();
		    			$('#myAddedTasksClicked').val(true);
		    			$('#filterStatusId').val(0);
		    			$('#filterUserId').val(0);
		    			//$('#filterCatId').val(0);
		    			filterFeeds(tId,0,0,false,0,false,userId,selectedOrder);
		    		}
		    		if(selectedTaskType == 1){//urgent
		    			tId = jQuery('#filterCatId').val();
			    		$('#urgentClicked').val(true);
						$('#filterStatusId').val(0);
						$('#filterUserId').val(0);
						//$('#filterCatId').val(0);
						filterFeeds(tId,0,0,true,0,false,null,selectedOrder);
		    		}
		    		if(selectedTaskType == 2){//live
		    			tId = jQuery('#filterCatId').val();
		    			$('#filterStatusId').val(2);
		    			$('#filterUserId').val(0);
		    			//$('#filterCatId').val(0);
		    			filterFeeds(tId,0,2,false,0,false,null,selectedOrder);
		    		}
		    		if(selectedTaskType == 3){//pending
		    			tId = jQuery('#filterCatId').val();
		    			$('#filterStatusId').val(3);
		    			$('#filterUserId').val(0);
		    			//$('#filterCatId').val(0);
		    			filterFeeds(tId,0,3,false,0,false,null,selectedOrder);
		    		}
		    		if(selectedTaskType == 4){//done
		    			tId = jQuery('#filterCatId').val();
		    			$('#filterStatusId').val(4);
		    			$('#filterUserId').val(0);
		    			//$('#filterCatId').val(0);
		    			filterFeeds(tId,0,4,false,0,false,null,selectedOrder);
		    		}
		    		
		    		if(selectedTaskType == 0){//all
		    			window.location = "index";
		    		}
		        	
		        	
		        });
	  
	  $("#orderBy").selectBox().change(
		        function() {
		        	selectedOrder = $(this).val();
		        	selectedTaskType = $("#selectTasksType").val();
		        	pageStart = 0;
		        	var catId = 0;
		        	var assignTo = 0;
		        	var status = selectedTaskType;
		        	var urgentStatus = false;
		        	var append = false;
		        	var userId = null;
		        	
		        	if(selectedTaskType == 'myTask'){
		        		status = 0;
		        		assignTo = $('#hiddenUserId').val();
		        	}
		        	if(selectedTaskType == 'myAddedTasks'){
		        		status = 0;
		        		userId = $('#hiddenUserId').val();
		        	}		        	
		        	if(selectedTaskType == 1){//urgent
		        		status = 0;
		        		urgentStatus = true;
		        	}
		        	
		        	filterFeeds(catId,assignTo,status,urgentStatus,pageStart,append,userId,selectedOrder);	
		        	
	   });
	  
	  $("#sourceType").selectBox().change(
		        function() {
		        	 if ($(this).val() > 0) {
		 		        $('#divToken').removeClass('hide');
		 		        $('#divUrl').removeClass('hide');
		 		    }
		 		    else {
		 		        $('#divToken').addClass('hide');
		 		        $('#divUrl').addClass('hide');
		 		       $('#url').val('');
		 		       $('#token').val('');
		 		    }
		        });
	  
	  $("#tasksViewType").selectBox().change(
		function (){
			var userId = $('#hiddenUserId').val();
			var viewTypeId = $("#tasksViewType").val();
				 			
			$('#listViewTypeSelected').val($("#tasksViewType").val());
			/*$.post('updateViewType',{viewType:viewTypeId,userId:userId});
			location.reload(true);*/
			
			$.post('updateViewType',{viewType:viewTypeId,userId:userId}, function(result) {
		        location.reload(true);
		    });
		});
	
	$('a:not(.selectBox, .selectBox-dropdown-menu a)').click(function() {
		$('#orderBy').selectBox('value', 0);
		$('#selectTasksType').selectBox('value', 0);
	});
       	
	
	
	//});
	/*$('#users').live("change", function(e) {
		filterFeeds($("#pageNumber").val());
		updateRemaining();
	});
	$('#allStatus').live("change", function(e) {
		filterFeeds($("#pageNumber").val());
	});*/
	
	jQuery.validator.addMethod(
			  "selectNone",
			  function(value, element) {
			    if (element.value != "0"){
			      return true;
			    }
			  },
			  "Please select a topic."
			);
	
	jQuery.validator.addMethod(
			  "validDate",
			  function(value, element) {
				  currentdate =  new Date();
				  if(element.value == '' ||  element.value >= currentdate.format("mm/dd/yyyy")){
					  return true;
			    }
			    else return false;
			  },
			  "Date should be greater than current date."
			);
	
	jQuery.validator.addMethod(
			  "validAssignee",
			  function(value, element) {
				  if(value.length > 0){
					  var assigneNames = $( 'span', '#assigneeDetail' ).map( function () {
						    return $( this ).data('assigneeName');
						}).get();
					   
					   var deptNames  = $( 'span', '#deptDetail' ).map( function () {
						    return $( this ).data('deptName');
						}).get();
					   
					   if(jQuery.inArray(value,$.merge( $.merge([],assigneNames), deptNames)) > -1){
						   return true;
					   }else return false;
				  }	else return true;		  
			    
			  },
			  "Assignee name is invalid."
			);
	
		$("#addNotes").validate({		
		rules: {
			feedSourceId:{ 
				selectNone: false
			},
			name: "required",
			summary: "required",
			assignEmailAndId: {
				validAssignee:true,
			},
		    url: {
		       url: true
		     },
			deadline: {
				validDate: true
			     }
			
		   },
		   messages: {
		     name: "Please enter title name.",
		     summary: "Please enter summary",
		     url: {
		       url: "Please enter valid url."
		     }
		   },
		   submitHandler: function(form) {
			
			   if($('#feedSourceId').val() == 0 ){
				   $('#selectTopicError').remove();
				   $('#topicSelectionArea').append("<div id='selectTopicError' style='color:red'>Please select a topic.</div>");
				   return false;
			   }
			   var deadline =  $("#deadline").val();
			   
			   japp.campaign.cancelAddCampaign();
			   var currentDate =  new Date(),
			   $container = $("#contentContainer"),
			   noOfComments = 0,
			   attachments = $('body').data('filelist'),
			   feedIdAndColour = $('#feedSourceId').val().split("-"),
			   taskObject = $("#newTaskObject").data("taskdetail"),
			   assigneeTextBox = $("#assignEmailAndId");
			   assignee = assigneeTextBox.attr("data-selected-item-id");
			   
			   
			   taskObject.feedSourceId=feedIdAndColour[0];
			   taskObject.name = $('#name').val();
			   taskObject.summary = $('#summary').val();
			   taskObject.deadline = deadline;
			   taskObject.assignToName = (assigneeTextBox.val().length != 0)?assigneeTextBox.val():'All';
			   taskObject.colour = feedIdAndColour[1];
			   taskObject.createdAt = currentDate;
			 
			   
			   if($('#status').is(':checked')){
				   taskObject.urgentStatus = true;					
				}
			   
			   
			  str = displayList(taskObject,noOfComments,currentDate,"active",attachments,attachments.length,taskObject,"jquery");
			  
			  $container.children(".campaign-line").removeClass("active");
			  
			  $container.prepend(str);
			  
			  if(deadline == ''){
				   deadline = new Date(1970,1,1);
			   }else{
				   deadline = $("#deadline").val();
			   }
			  
			  
			   //event.preventDefault();	
		//jQuery('#addForm').slideUp();
		
			  var myflag = false;
			  
		$.post('addTask', {
			feedSourceId:taskObject.feedSourceId,
			name: taskObject.name,
			summary:taskObject.summary,
			assignEmailAndId:assignee,
			assignToName:taskObject.assignToName,
			deadline:deadline,
			urgentStatus: $('#status').is(':checked'),
			fileIds: getFilelist()
				},
				function(result) {
					
					console.log(result);
					
					if (!myflag) {
						checkUpdateStatus(false,false);
                    }
                    myflag = true;
					
					
					
					/*jQuery('[data-control=replyBtn]').each(function(key,value) {
						
						if(key == 0){
							var self =  jQuery(this);
							
							console.log(self.find('a'));
							
							self.attr('id', result.id);
							self.find('#assignTo').val(result.assignTo);
							
						}						
						console.log(jQuery(this));
						console.log(key + ":value:" + value);
				    });
*/
					
					
					
					
					
					
					//clearForm();
					//japp.campaign.cancelAddCampaign();
					//hideShowBindReplyForm();
					//commonChecksAndDisplay(result,false);
                   // displayPaging(result.length);
					//checkUpdateStatus(false, false);	
                    
		});
		$('#selectTopicError').remove();
		clearForm();
	}
		   
	});
	
	$('#selRoleId').live("change",function(){

		$.post('updateRole',{
			roleId: $(this).val(),
			userId: $(this).closest("#userRoleInfo").find("#userId").val()
		},
		function(result){
			//alert("Role Updated");
		});
	});
	
	$('#replySubmitButton').live("click", function (event) {
		
		var rName = $("#assignTask").val();
		
		 if(rName.length > 0){
			  var assigneNames = $( 'span', '#reassigneeDetail' ).map( function () {
				    return $( this ).data('assigneeName');
				}).get();
			   
			   var deptNames  = $( 'span', '#reassignDept' ).map( function () {
				    return $( this ).data('deptName');
				}).get();
			   
			   if(jQuery.inArray(rName,$.merge( $.merge([],assigneNames), deptNames)) > -1){
				  // return true;
			   }else {
				   $('#reassignTaskBlock').append('<div class="float-right"> <label style="margin-right: 73px;color: #F70808;">Invalid Assigner Name!</label></div>');
				   return false;
			   }
		  }		
		 event.preventDefault();
		event.stopPropagation();
		hideShowBindReplyForm();
		japp.campaign.hideReply();
		//console.log($(this).parents('form').serialize());
		
		var parentFeedId = $('#parentId').val(),
			$commentsDiv  = $('#comments'+parentFeedId),
			strSub = "",
			dateCreated = new Date(),			
			attachments = $('body').data('filelist'),
			userObj =  $("#userObject").data("userdetail"),
			userImage = userObj.userImage,
			summary = $('#replySummary').val(),
			statusName = $('#replyStatus').data("replyStatus"),
			currentDate =  new Date(),
			reassignedUser = $("#assignTask").attr("data-selected-item-id"),
			reassigneeName = $("#assignTask").val();
		    
		
			if(reassignedUser != '' && reassigneeName != ''){
				summary = summary + "<br> Reassigned task to: <strong>"+ reassigneeName+ "</strong>";
			}
			
			strSub +=displaySingleSubTask(userObj,currentDate,dateCreated,summary,statusName,userImage,attachments,attachments.length);
			
			if($('#replyUrgentStatus').is(':checked')){
				$('#title'+parentFeedId).append('<div class="urgent"></div>');							
			}
			
			if($('#replyStatus').val() == 4){
				$("#taskContainer"+parentFeedId).remove();	
			}
			
		$commentsDiv.prepend(strSub);
				
		
		$.post('reply.htm', {
			feedSourceId:$('#sourceId').val(),
			name: $('#replyName').val(),
			summary:summary,
			url: $("#replyUrl").val(),
			status: $('#replyStatus').val(),
			fileIds: getFilelist(),
			parentId: $('#parentId').val(),
			taskCreatorEmail: $('#taskAssignerEmail').val(),
			assignTo:$('#assignToId').val(),
			assignToDept:$('#assignToDept').val(),
			taskUpdateEmailNotify:$('#replyPostCommentEmailNotify').val(),
			assignToName : $('#replyAssignToName').val(),
			assignerName : $('#replyAssignerName').val(),
			assignToEmail : $('#taskAssignToEmail').val(),
			taskTitle : $('#replyTaskTitle').val(),
			statusUrgent: $('#replyUrgentStatus').is(':checked'),
			reassignerUser: reassignedUser,
			reassigneeName: reassigneeName,
			taskCreatorId:$('#taskAssignerId').val()
				},
				function(result) {
					$.each(result, function(index, value) {
						
					});
					
					
					
					//commonChecksAndDisplay(result,false);
					clearReplyForm();
                  //  checkUpdateStatus(false, false);	
		});
		 
	});
	
	 $("#createUsers").validate({ 
			        rules: { 
		 	          email: { 
		 	          required: true, 
		 	          email: true 
		 	        }, 
		 	       firstName: "required",
		 	       lastName:"required"
		 	        
		 	        }, 
		 	        messages: { 
		 	        	email: "Please enter email ." ,
		 	        	firstName: "Please enter first name",
		 	        	lastName: "Please enter last name"
		 	        },
		 	       submitHandler: function(form) {
		 	    	  $.post('checkUserExists.htm', {
		 	    		email: $('.emailcheck').val() 
		 	    	  },function(result){
		 	    		  
		 	    		 if (!result.email) {
	                          form.submit();
	                      } else {
	                    	  var label = '<label for="email" generated="true" class="error" style="">Email exists.</label>';
	                         $('.emailcheck').after(label);
	                      }
		 	    	 
		 	    		 
		 	    	  });
		 	       }
		 	      }); 
	 
	 
	 $("#feedSourceForm").validate({
		 errorLabelContainer: "#messageBox",
			rules: {
				name: "required",
				checkedUserId: {
	                required: true
	            },
			    url: {
			       required: true,	
			       url: true
			    },
			    errorElement: "div",
			    wrapper: "div",
			    errorPlacement: function(error, element) {
		            offset = element.offset();
		            error.insertBefore(element);
		            error.addClass('message');  // add a class to the wrapper
		            error.css('position', 'absolute');
		            error.css('left', offset.left + element.outerWidth());
		            error.css('top', offset.top);
		        }
			},
 	        messages: { 
 	        	name: "Please enter topic name ." ,
 	        	checkedUserId: "Please select at least one user.",
 	        	url: "Please enter URL for rss feed."
 	        }
						
	 });
	
	$("input[id='searchBox']").change( function() {	
		var $stext="%"+$("#searchBox").val()+"%";
		$('#multiSourceFilter').val(false);
		$('#searching').val(true);
		loadingImage();
		searchFeeds($stext, 0,false);
	});
	
	$('#cancelAdd').click(function() {
		clearForm();
		//validator.resetForm();
	});
	
	$('#cancelUserForm').click(function() {
		clearUserForm($('#createUsers'));
	});
	
	
	
	$('#replyCancel').click(function() {
		clearReplyForm();
	});
	// Attachment in add notes
	$('#upload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            $.each(data.result, function (index, file) {
                $('body').data('filelist').push(file);
                container = $('#attachmentsCont');
                formatFileDisplay(file,container);
            });
        }
    });
	// Attachment in add notes
	$("#attach").live("click", function() {
	    $("#upload").trigger('click');
	});
	
	// Attachment in reply
	$('#replyUpload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            $.each(data.result, function (index, file) {
                $('body').data('filelist').push(file);
                container = $('#replyAttachmentsCont');
                formatFileDisplay(file,container);
            });
        }
    });
	// Attachment in reply
	$("#replyAttach").live("click", function() {
	    $("#replyUpload").trigger('click');
	});
	
	$('#profileImage').fileupload({
        dataType: 'json',
        done: function (e, data) {
        	if(data.result != null){
        		//alert(data.result.split("/"));
        		$("#profilePhoto").attr("src", profileImage + data.result.split("/")[7]);
        	}else{
        		alert("Image not uploaded! A valid image file is required.");
        	}
        }
    });
	
	
	$("#uploadImage").live("click", function() {
		$("#profileImage").trigger('click');
	});
	
	$('#profileInnerImage').fileupload({
        dataType: 'json',
        done: function (e, data) {
        	if(data.result != null){
        		//alert(data.result.split("/"));
        		$("#profileInnerPhoto").attr("src", profileImage + data.result.split("/")[7]);
        	}else{
        		alert("Image not uploaded! A valid image file is required.");
        	}
        }
    });
	
	
	$("#uploadProfileInnerImg").live("click", function() {
		$("#profileInnerImage").trigger('click');
	});
	
	
	
	$("#userInfo").validate({
		rules: {
		   },
		   messages: {
		   },
		   submitHandler: function(form) {
		//event.preventDefault();	
		$.post('updateUserInfo', {
			id:$('#id').val(),
			firstName:$('#firstName').val(),
			lastName: $('#lastName').val(),
			jobTitle:$('#jobTitle').val(),
			email:$('#email').val(),
			regionId:$('#regionId').val()
				},
				function(result) {
					if(result>0){
						location.reload(true);
						//window.location="preferences";
					}
		   });
		   }
		   
	});
	
	$("#userPass").validate({		
		rules: {
	          password: { 
	                required: true, minlength: 6
	          }, 
	          confirmPassword: { 
	                required: true, equalTo: "#password", minlength: 6
	          }, 
		  },
		   messages: {
		   },
		   submitHandler: function(form) {
		//jQuery('#userPass').slideUp();
		$.post('updateUserPassword.htm', {
			oldPassword:$('#oldPassword').val(),
			password:$('#password').val(),
			id:$('#id').val()
				},
				function(result) {
					if(result==0){
						$('#oldpasserror').removeClass('hide').addClass('show').css("display","");
					}else{
						/*japp.userDropDownReset();
						jQuery('[data-control=userMenu]').removeClass('show').slideUp();
						jQuery('[data-control=userMenuBtn]').removeClass('active');*/
						//history.go(0);
						location.reload(true);
						//window.location.href = "/journal.htm";
					}
		   });
		
		  }
		   
	});
	
	
	$("#follow").live("click", function(e){
		var threadId = jQuery(this).children("input").attr('value');
		var userId = $('#hiddenUserId').val();
		jQuery(this).attr('id','unfollow');
		jQuery(this).text('unfollow');			
		$.post('followThread.htm', {
			followerId:userId,
			id:threadId
		},
		function(result){
			//alert(result);
			if(result > 0)
				reloadFollowing(userId);	
		});
	});
	
	$("#unfollow").live("click", function(e){
		var threadId = jQuery(this).children("input").attr('value');
		var userId = $('#hiddenUserId').val();
		jQuery(this).attr('id','follow');
		jQuery(this).text('follow');
		$.post('followThread.htm', {
			followerId:0,
			id:threadId
		},
		function(result){
			//alert(result);
			if(result > 0)
				reloadFollowing(userId);	
		});
	});
	
	$("#disableUser").live("click", function(e){
		var userId = jQuery(this).children("input").attr('value');
		jQuery(this).attr('id','enableUser');
		jQuery(this).text('enable user');			
		$.post('enableDisableUser', {
			userId:userId,
			action:"disable"
		},
		function(result){
		});
	});
	
	$("#enableUser").live("click", function(e){
		var userId = jQuery(this).children("input").attr('value');
		jQuery(this).attr('id','disableUser');
		jQuery(this).text('disable user');			
		$.post('enableDisableUser', {
			userId:userId,
			action:"enable"
		},
		function(result){
		});
	});
	
	$("#assignedTaskEmailTrue").change(function(){
		
		$.post('updateAssignedTaskEmail', {
			taskAddEmail:"True"
		});	
		
	});
	$("#assignedTaskEmailFalse").change(function(){
		
		$.post('updateAssignedTaskEmail', {
			taskAddEmail:"False"
		});		
		
	});	
	$("#postCommentTrue").change(function(){
		
		$.post('updatePostCommentEmail', {
			taskUpdateEmail:"True"
		});			
		
	});
	$("#postCommentFalse").change(function(){
		
		$.post('updatePostCommentEmail', {
			taskUpdateEmail:"False"
		});		
		
	});
	
	var thread = null;
	
	$('#userSearchBox').keyup(function(){
		clearTimeout(thread);
		var $this = $(this);
		thread =  setTimeout(function(){
			findUser("%"+$this.val()+"%");
			}, 1000);
	});

	
	$('#prefList li a').on('click', function() {
		var $this = $(this);
		var userLoggedIn = {};
		$.post('getLoggedInUser', function(loggedInUser) {
			objList = loggedInUser;
			
			if($this.hasClass("pref1")){
				$.post('groupAccountInfo', function(result){
					var theTemplate = render('groupAccountInfo', result);
					 $("#accountInfo").html(theTemplate);
					
				});
			}
			
			if($this.hasClass("pref5")){
				if(!jQuery.isEmptyObject(objList)){
					$.post('displayUsers', function(allUsers) {
						var userObj = $.extend({users:allUsers},{user:objList[0]},{depts:objList[1]},{roles:objList[2]});
						var theTemplate =  render('userMgmt', userObj);
						$("#usersMgmtDiv").html(theTemplate);
					});
				}		
			}
			if($this.hasClass("pref7")){
				$.post('displayRegions', function(result) {
					var theTemplate = render('displayRegions', result);
					$("#regionTable").html(theTemplate);
				});
				$.post('displayDepts', function(result) {
					var theTemplate = render('displayDept', result);
					 $("#deptTable").html(theTemplate);
				});
				
			}
		});	
		
	});
	
	
	$('#usersMgmtDiv').children('[data-control=userBtn]').each(function() {
		 jQuery(this).live('click', function (e) {
	            e.preventDefault();
	            e.stopPropagation();
		 });
		
	});
	
	Handlebars.registerHelper("userProfileImage", function(userProfileImage) {
		var userImage;
		if(userProfileImage == null){
			userImage = "resources/images/blank.gif";
		}else{
			userImage = profileImage+userProfileImage;
		}
		return userImage;
	});
	
	Handlebars.registerHelper("userStatusButton", function(roleId) {
		if(roleId=="" || roleId == 0){
			return "enableUser";
		}else{
			return "disableUser";
		}
		
	} );
	Handlebars.registerHelper("userStatusButtonText", function(roleId) {
		if(roleId=="" || roleId == 0){
			return "enable user";
		}else{
			return "disable user";
		}
		
	} );
	
	Handlebars.registerHelper("checkSuperAdmin", function(loggedInUserRoleId, roleId,roles, options) {
		if(loggedInUserRoleId == 3 && roleId !=0 && roleId < 3){
			return options.fn(this);
		}else{
			return options.inverse(this);
		}
		
	} );

	Handlebars.registerHelper("checkIfHasRole", function(roleId, options) {
		if(roleId !=0){
			return options.fn(this);
		}
		return "";
		
	} );
	
	Handlebars.registerHelper("selectRole", function(userRoleId, roleId){
		if(userRoleId == roleId){
			return "selected";
		}else{
			return "";
		}
		
	});
	
	Handlebars.registerHelper("selectedDept", function(depts, selectedDeptId){
		flag =  false;
		$.each(depts, function(index, dept) {			
			if(dept.id == selectedDeptId){
				console.log(dept.name);
				flag = true;
			}			
		});
		if(flag)
			return "checked";
		else
			return "";
	});
	
	Handlebars.registerHelper("packageEDate", function(timestamp){
		if(timestamp != null)
		  return  "package runs until "+ (new Date(timestamp)).format("mmmm dd, yyyy");		
	});
	
	Handlebars.registerHelper("groupCDate", function(timestamp){
		  return (new Date(timestamp)).format("mmmm dd, yyyy");		
	});
	
	//Handlebars.registerPartial("displayRoles",$("#displayRolesTemplate").html());
	$("body").data("filelist", new Array());
}


function render(tmpl_name, tmpl_data) {
    if ( !render.tmpl_cache ) { 
        render.tmpl_cache = {};
    }

    if ( ! render.tmpl_cache[tmpl_name] ) {
        var tmpl_dir = 'resources/templates';
        var tmpl_url = tmpl_dir + '/' + tmpl_name + '.html';

        var tmpl_string;
        $.ajax({
            url: tmpl_url,
            method: 'GET',
            async: false,
            success: function(data) {
                tmpl_string = data;
            }
        });

        render.tmpl_cache[tmpl_name] = Handlebars.compile(tmpl_string);
        
       // var theTemplateScript = render('displayRegions', result); 
		//var theTemplate = Handlebars.compile (theTemplateScript);				
		//$("#regionTable").append(theTemplate(result));
        
    }

    return render.tmpl_cache[tmpl_name](tmpl_data);
}


function findUser(searchStr){
	$.post('filterUsers', {
		searchStr:searchStr
		},
		function(result){
		 console.log(result);		
	});
}

function modifyUserDept(formId){
	console.log("form"+$('#'+formId));
	console.log("form2"+$('#'+formId).serialize());
	$.ajax({
		type: 'POST',
		url: 'modifyUserDept',
		data: $('#'+formId).serialize(),
		done:function(data){
			console.log("form"+data);
		}
	});
	
}

function resetAllVariable(){
	$('#multiSourceFilter').val(false);
	$('#searching').val(false);
	$('#followingByCat').val(false);
	$('#pastDueTasks').val(false);
	$('#dueTodayTasks').val(false);
	$('#urgentTasks').val(false);
	$('#todayPendingTasks').val(false);
	$('#myAddedTasksClicked').val(false);
	$('#urgentClicked').val(false);
	$('#displayAllUsersTasks').val(false);
	$('#imgLoader').remove();
	pageStart=1;
				
	loadingImage();
}

function showUserTasks(vthis){
	resetAllVariable();
	if(vthis.attr('id').substr(0,6) == 'myTask'){
		var myId = vthis.attr('id').substr(6);
		$('#filterUserId').val(myId);
		$('#filterStatusId').val(0);
 		$('#filterCatId').val(0);
		filterFeeds(0,myId,0,false,0,false);
	}
}

function loadingImage(){
	//$('#imgLoader').remove();
	imageLoadCheck = 1;
	$container = $("#contentContainer");
	$container.empty();	
	$container.append('<div style="width: 10%; margin:auto;"><img src="resources/images/ajax-loader_c.gif" alt="loading..." title="loading..."  /></div>');
}

function commonChecksAndDisplay(result,append){
	$container = $("#contentContainer");
	if(!append){
		$container.empty();	
	}	
	currentDate =  new Date();
	if(result.length>0){
		$.each(result, function(index, value) {
			var activeFirstTask = "";
			var str = "";
			
			if(index == 0){
				activeFirstTask = 'active';
			}
			
			
			str = displayList(value[0],value.length-1,currentDate,activeFirstTask,value[0].fileAttachment,value[0].attachmentCount,value,"java");
			
			$container.append(str);	
			imageLoadCheck = 0;
			});
	}else{
		jQuery('#noRecords').remove();
		$container.append('<h1 id="noRecords" class="content-title">No records found!</h1>');
	}
	if(append){
		$('#imgLoader').remove();
		imageLoadCheck = 0;
	}
	japp.campaign.bindButtons();
	
}

function deleteFeed(deleteId){
	$.post('deleteFeed',{
		id:deleteId
		},function(result) {
			
		if(result){
			checkUpdateStatus(true,false);
		}
		
	});
}

function deleteSource(deleteId){
	$.post('deleteSource',{
		id:deleteId
		},function(result) {
			
		if(result){
			//alert();
			 window.location="manageSources";
		}
		
	});
}

function deleteUser(deleteId){
	$.post('deleteUser',{
		id:deleteId
		},function(result) {
			
		if(result){
			//alert();
			window.location="preferences?tabSelected=5";
		}
		
	});
}

function hideShowBindReplyForm()
{

	japp.campaign.hideReply();
	 
}

function searchFeeds(stext,pageStart,append){
	console.log(stext);
	$.post("search",{searchText:stext,
	pageStart:pageStart	
	},
			function(result){
			hideShowBindReplyForm();
			commonChecksAndDisplay(result,append);
			//displayPaging(result.length);
	});	
}

function updateFeedSources(userId){
	var catArray =  new Array();
	var strFeeds = "";
	
	
	$.post('selectEnabledFeedSource.htm', {
		userId:	userId},
			function(catIds) {
				$('li.new-item').remove();
				$.each(catIds, function(index, catId) {
					catArray[index] =  catId;
			});
		});

	$.post('getAllSources.htm',{userId:	userId},
			function(allSources) {
	
 $.each(allSources, function(index, feedCategory) {
	 	var removeClass = 'gray';
	 	var show_hide = 'Hide' ;
	 	var subtopicCss = "";
	 	var strSubtopics = "";
	 	if(jQuery.inArray(feedCategory['id'],catArray)>=0){
	 		removeClass = '';
	 		show_hide = 'Show';
	 	}
	 	if(feedCategory.subTopics.length >0){
	 		subtopicCss = 'data-control="dropdownExpander" class="left-nav-ddwn-item collapsed"';
	 		 $.each(feedCategory.subTopics, function(index, subtopic) {
					strSubtopics += "<li><i class='icon-ball'><i style='background:"+ subtopic['subtopicColor'] +";'></i></i> <a id=source"+subtopic['subtopicId']+ " href='#'>" +subtopic['subtopicName']+ "</a></li>";
				 });
	 	}
	 strFeeds +="<li class='new-item "+removeClass+"'><i class='icon-ball'><i style='background:"+feedCategory['colour']+ "';>" +
			"</i></i><a "+ subtopicCss +" id=source"+ feedCategory['id'] + " href='#'> " + feedCategory['name'] + " </a>" +
	 		"<input type='hidden' id='catIdForShowHide' value="+feedCategory['id']+">" +
	 		"<span class='edit-row' id='showHideId'><a href='#' data-control='editNavRow'>"+show_hide+"</a>" +
			"</span>" +
			"<ul class='left-nav-dropdown accordion-hide'>" + strSubtopics +
			"</ul>" + 			
			"</li>";
	 
 });
 
 	$("#allSourcesList").append(strFeeds);
 
	});	
}

function reloadFollowing(userId){
	strFollowCats = '';
	$.post('reloadFollowing.htm', {
		followerId:	userId},
			function(followCats) {
				$('li.new-follow').remove();
				$.each(followCats, function(index, followingCat) {
					strFollowCats += '<li class="new-follow"><i class="icon-ball">'
						+'<i style="background: ' + followingCat.colour + ';"></i></i>'
						+'<a id="followingCat" href="#">'+followingCat.name+' <input type="hidden" id="followingCatId" value='+followingCat.id+'></a></li>';
			});
			$("#allFollowingList").append(strFollowCats);
		});
	
}

function checkSessionTimeOut(){
	checkMouseOver =false;
	if(!checkMouseOver){
		checkMouseOver = true;
		$.post('checkUserLoggedin',function(result) {
			if(result == "sessiontimeout"){
				alert("Your session has timed out.Please login again.");
				window.location.replace("login.htm");
			}
		});
	}
}
	

function formatFileDisplay(file,attachmentCont) {
	//var size = '<span style="font-style:italic">'+(file.attachmentFileSize/1000).toFixed(2)+'K</span>';
	//var fileName = "<br/><span class='form-attached'><span id='"+spanId+"'>"+file.name+"("+(file.attachmentFileSize/1000).toFixed(2)+"K)</span> <i id='"+file.id+"' class='icon-del'></i></span>";
	attachmentCont.append('<span class="form-attached"><span>'+file.name +'('+(file.attachmentFileSize/1000).toFixed(2)+'K)</span> <i id='+file.id+' class="icon-del"></i></span>&nbsp;');
	//return anchorId.after(fileName);
}

$('.icon-del').live("click", function(e) {
	var fileId = $(this).attr('id');
	$.post('deleteFile.htm', {
		fileId:	fileId},
			function(result) {
			
			if(result){
			  var files = $('body').data('filelist');
			  for (var i=0; i<files.length; i<i++) {
				  if(files[i].id == fileId)
					  $('body').data('filelist').splice(i,1);
			  }
			  $('#'+fileId).parent().remove();
			}
			});
});

function filterFeeds(catId,assignTo,status,urgentStatus,pageStart,append,userId,orderBy){
	$.post('filter.htm', {
		feedSourceId:catId,
		assignTo:assignTo,
		status: status,
		urgentStatus:urgentStatus,
		userId:userId,
		pageStart:pageStart,
		orderBy:orderBy
			},
			function(result) {
				hideShowBindReplyForm();
					commonChecksAndDisplay(result,append);
				
	});
	//pageStart = pageStart+1;	
}

function multiSourceFilterFeeds(userId,pageStart,append){
	$.post('multiSourceFilterFeeds.htm', {
		userId:userId,
		pageStart:pageStart
			},
			function(result) {
				hideShowBindReplyForm();
					commonChecksAndDisplay(result,append);
	});
	//pageStart = pageStart+1;	
}

function followedTasksInCat(followingCatId,followerId,pageStart,append){	
	$.post('threadsFollowedInCat.htm', {
			pageStart:pageStart,
			followerId:followerId,
			feedSourceId:followingCatId
		},
		function(result){
				commonChecksAndDisplay(result,append);
		});
	}

function displayOldTasksPending(userId,pageStart,append){//past due tasks
	$.post('displayOldTasksPending', {
		pageStart:pageStart,
		userId:userId
	},
	function(result){
			commonChecksAndDisplay(result,append);
	});
}

function displayTodaysDueTasks(userId,pageStart,append){ //Due Today
	$.post('displayTodaysDueTasks', {
		pageStart:pageStart,
		userId:userId
	},
	function(result){
			commonChecksAndDisplay(result,append);
	});
}

function displayUrgentTasks(userId,pageStart,append){ //urgent Tasks
	$.post('displayUrgentTasks', {
		pageStart:pageStart,
		userId:userId
	},
	function(result){
			commonChecksAndDisplay(result,append);
	});
}

function displayTodaysTasksPending(userId,pageStart,append){ // New Tasks
	$.post('displayTodaysTasksPending', {
		pageStart:pageStart,
		userId:userId
	},
	function(result){
			commonChecksAndDisplay(result,append);
	});
}

function displayAllMyTasks(userId,pageStart,append,orderBy,topicId){ // All User logged in Tasks
	$.post('displayAllMyTasks', {
		pageStart:pageStart,
		userId:userId,
		orderBy:orderBy,
		feedSourceId:topicId
	},
	function(result){
			commonChecksAndDisplay(result,append);
	});
}



function recentlyViewedThread(viewerId,catId,append){
	$.post('threadRecentlyViewed.htm', {
		viewerId:viewerId,
		id:catId
	},
	function(result){
		//if(result.length > 0)
			commonChecksAndDisplay(result,append);
		//else{}
			//alert("No records.");
	});
}

function deleteSubTopic(id){
	$.post('deleteSubtopic', {
		id:id
	},
	function(result){
	});
}


function selectlastViewedThreads(userId){	
	strLastViewed ='';
	$.post('selectlastViewed.htm', {
		userId:	userId},
			function(lastViewedList) {
				$('li.recently-viewed').remove();
				$.each(lastViewedList, function(index, lastViewed) {
					strLastViewed += '<li class="recently-viewed">'
						+'<a id="recentlyViewedId" href="#">'+lastViewed.name+'<input type="hidden" id="lastViewedId" value='+lastViewed.id+'></a></li>';
			});
				$("#recentlyViewedTh").append(strLastViewed);
	});
}


function showHideTopic(catId,userId,action){
	pageStart = 1;
	loadingImage();
	$.post('showHideTopic.htm', {
		userId:userId,
		catId:catId,
		action:action
			},
			function(resultCount) {
				if(resultCount > 0){
					multiSourceFilterFeeds(userId, 0,false);
				}
			});
	
} 

function updateRecentlyViewed(viewerId, catId){
	$.post('updateRecentlyViewed.htm', {
		viewerId: viewerId,
		id:catId
			},
			function(resultCount) {
				
				if(resultCount > 0){
					selectlastViewedThreads(viewerId);
				}
				
			});
	
} 

function checkOnlineUsers(){
	$.post('getOnlineUsers',{}, function(result){
		if(result.length != undefined && result.length > 0){
			container =  $("#onlineUsersContainer");
			displayUsers(result,container,1);
			//getofflineUsers();
		}
	});
}
function getofflineUsers(){
	$.post('getOfflineUsers',{}, function(result){
		container =  $("#offlineUsersContainer");
		displayUsers(result,container,0);
	});
}

function displayUsers(onlineUsers,container,checkUserType){
	//var container =  $("#onlineUsersContainer");
	addOfflineClass = "";
	if(checkUserType == 0 ){
		addOfflineClass = "green offline";
	}
	
	var strUser = "";
	 container.empty();
	 $.each(onlineUsers, function(index, onlineUser) {
		 $('#myTask'+onlineUser['id']).remove();
		 userProfileImage = (onlineUser['profileimage'] == null)?"resources/images/blank.gif":profileImage+onlineUser['profileimage'];
		 jobTitle = onlineUser['jobTitle']!= null ? onlineUser['jobTitle'] :"";
		 firstName = onlineUser['firstName']!=null ?onlineUser['firstName']:"";
		 lastName = onlineUser['lastName']!=null ?onlineUser['lastName']:"";
		 strUser += '<li>'+ 
		        	'<a id="myTask'+onlineUser['id']+'" href="#" class="left-nav-user' + addOfflineClass +'">'+
		        	'<img src="'+userProfileImage+'" width="32" height="32" title="'+onlineUser['email']+'">'+
		        		'<span>'+
		        			'<strong>'+firstName+' '+ lastName+'</strong>'+
		        			 jobTitle + ' ('+ onlineUser['regionName'] + ')'+ 
		        			'</span>'+
		        	'</a>'+
		        	'</li>';
	 });
	 container.append(strUser);
}
	 
	 

function checkUpdateStatus(deleted, edited){
	
	var catId = $('#filterCatId').val();
	var assignTo = $('#filterUserId').val();
	var loggedInUserId = $('#hiddenUserId').val();
    var status = $('#filterStatusId').val();
    var myflag = false;
	$.post('checkUpdates',{
		deleted:deleted,
		edited:edited,
		feedSourceId:catId,
		assignTo:assignTo,
		loggedInUserId: loggedInUserId,
		status: status 
	}, function(result) {
				
		if(result.length == undefined)
			result = "";
		
		
		if(result.length!= 0 || deleted == true || edited == true){
			if (!myflag) {
			
				updateTaskMenu();
	//			 updateRemaining();
				//hideShowBindReplyForm();
				var replyForm = jQuery('[data-control=replyForm]'),
		        parent = replyForm.parents('[data-control=replyWrapper]');
	
				if (parent && parent.hasClass('show')) {
					return;
				}
			
			 
				 commonChecksAndDisplay(result,false);
             }
             myflag = true;
			
		}
	});
	
	
}


function updateTaskMenu(id){
	$.post('updateTaskMenu', {
		userId:$('#hiddenUserId').val()
	},
	function(result){
		jQuery('[data-control=tasksMenuBtn]').empty();
		var oldPending = result[0].split("-");
		var newPending = result[3].split("-");
		if(oldPending[1] > 0){
			jQuery('[data-control=tasksMenuBtn]').append('Tasks  <span class="past">'+newPending[1]+'</span>');
		}else{
			jQuery('[data-control=tasksMenuBtn]').append('Tasks  <span class="">'+newPending[1]+'</span>');
		}
		
		var displayOldTasksPending;
		var oldTasksPending; 
		var oldTasksPendingClass; 
		var oldTasksPendingSpan;
		
		var displayTodaysDueTasks;
		var todaysDueTasksClass;
		var todaysDueTasksSpan;
		
		var displayUrgentTasks;
		var urgentTasksSpan;
		
		var displayTodaysTasksPending;
		var todaysTasksPending; 
		var todaysTasksPendingClass;
		var todaysTasksPendingSpan;
		var displayAllMytasks;
		
		$.each(result, function(index, tasksCount) {
			var tasksCountParts = tasksCount.split("-");
			if(tasksCountParts[0] == 'displayOldTasksPending'){
				 displayOldTasksPending = tasksCountParts[0];
				 oldTasksPending = tasksCountParts[1];
				 oldTasksPendingClass = "past";
				 oldTasksPendingSpan = "Past Due <span>"+tasksCountParts[1]+" tasks</span>";
			}
			if(tasksCountParts[0] == 'displayTodaysDueTasks'){
				 displayTodaysDueTasks = tasksCountParts[0];
				 todaysDueTasksClass = "even";
				 todaysDueTasksSpan = "Due Today <span>"+tasksCountParts[1]+" tasks</span>";
			} 
			if(tasksCountParts[0] == 'displayUrgentTasks'){
				 displayUrgentTasks = tasksCountParts[0];
				 urgentTasksSpan = "Urgent<span>"+tasksCountParts[1]+" tasks</span>";
			}
			if(tasksCountParts[0] == 'displayTodaysTasksPending'){
				displayTodaysTasksPending = tasksCountParts[0];
				 todaysTasksPending = tasksCountParts[1];
				 todaysTasksPendingClass = "even";
				 todaysTasksPendingSpan = "New <span>"+tasksCountParts[1]+" tasks</span>";
			}
		 });
		var  totalTasks = parseInt(oldTasksPending) + parseInt(todaysTasksPending);
		str = '<input type="hidden" id="pastDueTasks" value="false">'+
              '<input type="hidden" id="dueTodayTasks" value="false">'+
              '<input type="hidden" id="urgentTasks" value="false">'+
              '<input type="hidden" id="todayPendingTasks" value="false">'+
              '<input type="hidden" id="displayAllUsersTasks" value="false">'+
              '<a id="'+ displayOldTasksPending +'" href="'+ displayOldTasksPending +'"'+ oldTasksPendingClass +'">'+ oldTasksPendingSpan +'</a>'+
              '<a id="'+ displayTodaysDueTasks +'" href="displayTodaysDueTasks" class="'+ todaysDueTasksClass +'">'+ todaysDueTasksSpan +'</a>'+
              '<a id="'+ displayUrgentTasks +'" href="'+ displayUrgentTasks +'">'+ urgentTasksSpan +'</a>'+
              '<a id="'+ displayTodaysTasksPending +'" href="'+ displayTodaysTasksPending +'" class="'+todaysTasksPendingClass+'">'+ todaysTasksPendingSpan +'</a>'+
              '<a id = "displayAllMyTasks" href="displayAllMyTasks">Total <span>'+ totalTasks +' tasks</span></a>';
		
		
			jQuery('[class=module-table]').empty();
			jQuery('[class=module-table]').append(str);
		
	});
	
	
}

function updateRemaining(){
	
	 $.post('updateRemaining',{
		feedSourceId:$('#feedSourceIdForFilter').val(),
		assignTo:$("#users").val()
		  },function(remainingNumber) {
		 
		  $('.task-remain').text(remainingNumber + " remaining tasks");
		  
	  });
	
}

function readRSSFeeds(){
	
	$.post('reedRssFeeds',function(result) {
		
	});
	
}

function getFilelist() {
	var files = $('body').data('filelist');
	var fileids = '';
	for (var i=0; i<files.length; i<i++) {
		var suffix = (i==files.length-1) ? '' : ',';
		fileids += files[i].id + suffix;	}
	return fileids;
}

function getFileNames() {
	var files = $('body').data('filelist');
	var fileNames = '';
	for (var i=0; i<files.length; i<i++) {
		var suffix = (i==files.length-1) ? '' : ',';
		fileNames += files[i].name + suffix;	}
	return fileNames;
}

function dialog(title, text) {
	$('#msgbox').text(text);
	$('#msgbox').dialog( 
			{	title: title,
				modal: true,
				buttons: {"Ok": function()  {
					$(this).dialog("close");} 
				}
			});
}

function clearForm() {
	$("#feedSourceId").selectBox('value', null);
	$('#assignTo').selectBox('value', null);
	$('#name').val('');
	$('#summary').val('');
	$('#url').val('');
	$('#deadline').val('');
	$('#filename').empty();
	$('input[name=status]').attr('checked', false);
	$('#attachmentsCont').empty();
	$('.form-attached').remove();
	$('body').data('filelist', new Array());
	$('#assignEmailAndId').val('');
	$("#assignEmailAndId").attr("data-selected-item-id",'');
}  

function clearReplyForm() {
	$('#replyName').val('');
	$('#replySummary').val('');
	$('#replyUrl').val('');
	$('#replyStatus').selectBox('value', 2);
	 jQuery('[data-control=statusCurrent]').html('');
	jQuery('[data-control=statusCurrent]').append('<span class="status-item active"><i class="icon-on-it"></i> onit</span>');
	$('input[name=replyUrgentStatus]').attr('checked', false);
	$('#replyFilename').empty();
	$('#replyAttachmentsCont').empty();
	$('.form-attached').remove();
	$('body').data('filelist', new Array());
	$('#assignTask').val('');
	$("#assignTask").attr("data-selected-item-id",'');
} 

function clearUserForm($form) {
    jQuery('[data-control=addUserForm]').removeClass('show').addClass('hide');
    jQuery('[data-control=addUserBtn]').removeClass('active');
    clearAnyForm($form);
    $form.find('#regionId').selectBox('value', 1);
    $form.find('#roleId').selectBox('value', 3);
    
   
    
    //$('#createUsers')[0].reset();
}  

function clearAnyForm($form){
	 $form.find('input:text, input:password, input:file, select, textarea').val('');
	    $form.find('input:radio, input:checkbox')
	         .removeAttr('checked').removeAttr('selected');
}



function displayList(value,noOfComments,currentDate, activeFirstTask,attachments,attachmentCount,allSubTasks,taskObjectType){
	
	var str = "";
	
//	console.log("assignToName"+ value.assignToName);
	
	var midnightDate = new Date();
	midnightDate.setHours(0,0,0,0);
	remainingDays = "";
	var strExcerptView = "";
	
	
	d = new Date(value.createdAt);
	date =  d.format("hh:MM TT");
	displayDate = "Today";
	assignToUsername = "All";
	
	if(value.assignToName != null)
		assignToUsername = value.assignToName;
	
	if(value.deptName != null)
		assignToUsername = value.deptName;
	
	if(currentDate.toDateString() != d.toDateString()){
		displayDate = d.format("dd mmm yyyy");
	}
	
	strEditDeleteEnable = "";
	if(value.userId == $('#hiddenUserId').val() && (currentDate/1000 - d/1000) <  600 && value.feedType == 0){
	strEditDeleteEnable = "<div class='close-box'>" +
						"<a id = 'editFeed' href='#' data-control='editCampaign'></i>" +
						"" +
						"<a id = 'deleteFeed' href='#' data-control='delCampaign'><i class='icon-close-box'></i>" +
						"<input type='hidden' id='deleteFeedId' value="+value.id+" name='deleteFeedId'/></a>" +
					"</div>";
	}
	
	if(value.deadline !=null && value.deadline !=''){
		
		deadline = new Date(value.deadline);
		remainingDays = ', Remaining days: '+
	         '<span>'+Math.round((deadline - midnightDate)/86400000)+'</span>';
		
	}
	 
	
	var strStatusIcon = getStatusIcon(value);
	
	var urgentStatus= '';
		
		if(value.urgentStatus == true || value.status == 1){
			urgentStatus = "<div class='urgent'></div>" ;
		}	
	
	var filesAttached = attachments;
	strAttach ="";
	if(attachmentCount > 0){
		 $.each(filesAttached, function(index, att) {
			strAttach += "<div><a href='" + attachmentsPath + att['attachmentId']+att['fileExtension']+"' class='btn-control attach' target='_blank' download = '"+att['name']+"'>" +
					"<i class='icon-attach'></i><span>"+att['name']+" </span></a></div>";
		 });
	}
	 firstName = value.firstName!=null ?value.firstName:"";
	 lastName = value.lastName!=null ?value.lastName:"";
	 
	 if($('#listViewTypeSelected').val() == ''){
	 
		  strExcerptView = "<div class='campaign-body'>" +
					"<div class='campaign-body-title'>" +
					"<span class='nick'>"+firstName+" "+ lastName +"("+value.regionName+")</span>," +
					"<span class='date'> "+date+", "+displayDate+"</span>" +
				"</div>" +
					"<div class='campaign-body-txt' data-control='campaignShowMore'>" +
						"<input type='hidden' value='"+value.id+"' id='viewed' >" +
						"<div class='campaign-body-trim'>"+
							value.summary +
							"</div>" +
							"<div class='show-more-wr'>" +
							"<a href='#' class='showMore'>show more</a>" +
							"</div>"+
					"</div>" +
				"</div>" ;
	 }
	
	str += "<div id='taskContainer"+value.id+"' class='campaign-line " + activeFirstTask + "' data-control='campaignWrapper' data-taskId='"+value.id+"'>" +
			"<div style='background:"+ value.colour + ";' data-control='campaign' class='campaign-wrapper'>" +
				"<div class='campaign-container'>" +
				"<div id='title"+value.id+"' class='campaign-title-wr' data-control='campaignShowMore'>" +
					"<i class='"+strStatusIcon+"'></i>" +
					"<span class='campaign-title'>" + value.name + "</span>" +
					"<i class='icon-arrow-right'></i>" +
						"<div class='close-box'>" +
							strEditDeleteEnable +
						"</div>" +
						urgentStatus +
				"</div>" +
				"<div class='campaign-content'>" +
					strExcerptView +
				"<div class='campaign-info'>" +
					"Assigned to: <span>"+assignToUsername+"</span>" + remainingDays +
					"<span class='float-right'>&bull; <a id='"+((value.follower > 0)?'unfollow':'follow')+"' href='#'>" +((value.follower > 0)?'unfollow':'follow')+
					"<input type='hidden' id='followFeedId' value=" + value.id + " name='followFeedId'/> </a>&nbsp;&bull;" +
						"<a href='#' data-control='campaignShowMore'>comments ("+ noOfComments +")</a>" +
					"</span>" +
				"</div>" +
			"</div>" +
		"</div>" +
	"</div>" +
	"<div class='more-wrapper' data-control='campaignMore'>" +
		"<div class='more-container'>" +
			"<div class='more-title-wrapper'>" +
				"<div class='more-title'>"+ value.name +"</div>" +
					"<div class='more-info'>" +
						"by:" +
						"<strong>"+ firstName +" "+lastName+ "</strong>," +
						"<span><span class='date'> "+date+", "+displayDate+"</span></span>" +
					"</div>" +
				"</div>" +
				"<div class='more-content'>" +
					"<pre>"+value.summary+"</pre>" +
				"</div>" +
				strAttach +
				"<div class='reply-wrapper hide' data-control='replyWrapper'>" +
				"</div>"+
				"<div class='comments-wrapper'>" +
					"<div class='comments-title-wr clearFix'>" +
						"<span class='comments-title'>COMMENTS ("+ noOfComments +")</span>" +
						"<span class='float-right'><a id='"+ value.id  +"' href='#' class='write-comment' data-control='replyBtn'>add comment" +
						"<input type='hidden' id='feedSourceIdForThis' value = "+ value.feedSourceId + " name = 'feedSourceIdForThis'/>" +
						"<input type='hidden' id='assignTo' value= "+ value.assignTo + " name='assignTo'/>" +
                        "<input type='hidden' id='taskCreatorEmail' value='"+ value.taskCreatorEmail + "' name='taskCreatorEmail'/>" +
                        "<input type='hidden' id='postCommentEmailNotify' value= '"+ value.taskUpdateEmailNotify + "' name='postCommentEmailNotify'/>" +
                        "<input type='hidden' id='assignToName' value= '"+ value.assignToName + "' name='assignToName'/>" +
                        "<input type='hidden' id='assignerName' value='"+ firstName +" "+lastName+ "' name='assignerName'/>" +
                        "<input type='hidden' id='taskTitle' value= '"+ value.name +"' name='taskTitle'/> " +
                        "<input type='hidden' id='taskCreatorId' value="+ value.userId +" name='taskCreatorId'/>" +
                        "<input type='hidden' id='taskAssigneeEmail' value="+ value.assignToEmail +" name='taskAssigneeEmail'/>" +
                        "<input type='hidden' id='assignToDept' value="+ value.assignToDept +" name='assignToDept'/>" +
                        "</a></span>" +
					"</div>";
	
	var strSub = "";
	strSub = displaySubstasks(allSubTasks, currentDate,taskObjectType);

				str +=strSub;
				return str +="<div class='comments-controls clearFix'>" +
					"<span class='float-right'>" +
					"&bull; <a id='follow' href='#'>follow" +
					"<input type='hidden' id='followFeedId' value="+ value.id+" name='followFeedId'/></a>" +
					"<input type='hidden' id='feedSourceIdForThis' value="+ value.feedSourceId + " name='feedSourceIdForThis'/></a>" +
					"</span>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"<div class='task-arrow'></div>" +
					"</div>" +
					"</div>";
				
							
}

function displaySubstasks(value, currentDate,taskObjectType){
	var strSub = "",
	       userImage = "";
	if(taskObjectType == "java")
		strSub = "<div id=comments" + value[0].id + ">" ;
	else
		strSub = "<div id=comments" + value.id + ">" ;
		
	$.each(value, function(index, subValue) {
		if(index > 0){
		dateCreated = new Date(subValue['createdAt']);
		date =  dateCreated.format("hh:MM TT");
		
		userImage = (subValue.profileImage == null)?'':subValue.profileImage;
		
		strSub += displaySingleSubTask(subValue,currentDate,dateCreated,subValue.summary,subValue.statusName,userImage,subValue['fileAttachment'],subValue['attachmentCount']);
	 }
		
	});
	strSub +="</div>" ;
	return strSub;
}

function displaySingleSubTask(subValue,currentDate,dateCreated,summary,statusName,userImage,attachedFiles,attachmentCount){
	
	var strSub = "";
	displayDate = "Today";
	pImage = (userImage == '')?"resources/images/blank.gif":profileImage + userImage;
	
	if(currentDate.toDateString() != dateCreated.toDateString()){
		displayDate = dateCreated.format("dd mmm yyyy hh:MM TT");
	}
	
	var attachments = attachedFiles;
	strAttach ="";
	
	if(attachmentCount > 0){
		 $.each(attachments, function(index, att) {
			strAttach += "<a href='" + attachmentsPath + att['id']+att['fileExtension']+"' class='btn-control attach' target='_blank' download = '"+att['name']+"'>" +
					"<i class='icon-attach'></i><span>"+att['name']+" </span></a>";
		 });
		 		 
	}
	
	strSub += "<div class='comment clearFix'>" +
					"<div class='comment-img'>" +
						"<img src='"+pImage+"' height='56'>" +
					"</div>" +
					"<div class='comment-txt'>" +
						"<div><pre>"
						+ summary +
						"</pre></div>" +
						"<div>"
						+ strAttach +
						"</div>" +
						"<div class='comment-info clearFix'>" +
							"<span class='float-left comment-info-name'><strong>"+ subValue.firstName +" "+subValue.lastName +"</strong> - "+((subValue.jobTitle == null)? '':subValue.jobTitle) +" ("+subValue.regionName + ")" +
									"<br>changed status to:"+statusName+"</span>" +
							"<span class='float-right comment-info-day'>"+ displayDate +"</span>" +
						"</div>" +
					"</div>" +
					"</div>";
	
	return strSub;

	
}
	

function getStatusIcon(campaign){
	
	var taskStatusIcon = "icon-rss";
	if(campaign.status == 2){
		taskStatusIcon = "icon-on-it";
	}
	if(campaign.status == 3){
		taskStatusIcon = "icon-pending";
	}
	if(campaign.status == 4){
		taskStatusIcon = "icon-don";
	}
	
		return taskStatusIcon;
	
}

