var japp = japp || {};
var pageStart =1;
var imageLoadCheck = 0;
var subtopicArray = new Array();


japp.global = window;
japp.document = window.document;
japp.body = window.document.body;

japp.selectBoxes = function () {
    if (!(jQuery.browser.msie === true && jQuery.browser.version === '7.0')) {
        jQuery("select[data-custom=true]").selectBox();
    }
};

japp.layout = japp.layout || {};

japp.layout.bodyHeight = function () {
    return jQuery('body').innerHeight() - 5;
};

japp.layout.colsHeight = function () {
    var bodyHeight = japp.layout.bodyHeight(),
        left = jQuery('#contentLeft'),
        leftScroll = jQuery('#contentLeftScroll'),
        right = jQuery('#contentRight'),
        rightScroll = jQuery('#contentRightScroll');



    if (left.size() > 0 || right.size() > 0) {
        left.height(bodyHeight - 64);
        right.height(bodyHeight - 59);
    }

    if (leftScroll.size() > 0 || rightScroll.size() > 0) {
        leftScroll.height(jQuery(left).height() - 54);
        rightScroll.height(jQuery(right).height() - 65);

    }
};


japp.layout.scrollShadow = function () {
    var bodyHeight = japp.layout.bodyHeight(),
        left = jQuery('#contentLeft'),
        leftScroll = jQuery('#contentLeftScroll'),
        right = jQuery('#contentRight'),
        rightScroll = jQuery('#contentRightScroll'),
        flag = 0,
        flag1 = 1,
        topGrad = jQuery('<div class="right-scroll-shadow"></div>'),
        botGrad = jQuery('<div class="right-scroll-shadow-bot"></div>');



    if (leftScroll.size() > 0 || rightScroll.size() > 0) {
        right.prepend(topGrad);
        right.append(botGrad);

        if ((rightScroll[0].scrollHeight - rightScroll[0].clientHeight) > 10) {
            botGrad.show();
        } else {
            botGrad.hide();
        }
        topGrad.hide();


        rightScroll.scroll(function (e) {
            var piu = (rightScroll[0].scrollHeight - rightScroll[0].clientHeight) - jQuery(e.target).scrollTop();
            
            var replyForm = jQuery('[data-control=replyForm]'),
            parent = replyForm.parents('[data-control=replyWrapper]');

            if (parent && parent.hasClass('show')) {
            	flag1 = 0;
            }else{
            	flag1 = 1;
            }
            	
            if (jQuery(e.target).scrollTop() > 10) {
                if (flag === 0) {
                    topGrad.show();
                    flag = 1;
                }
            }
            if (jQuery(e.target).scrollTop() < 10) {
                topGrad.hide();
                flag = 0;
            }
           
            if (piu < 10) {
            if(imageLoadCheck == 0){	
                if (flag1 === 1) {
                    botGrad.hide();
                    flag1 = 0;
                    var rowStart = (pageStart*15);
                    var catId = $('#filterCatId').val();
             	   	var filterUserId = $('#filterUserId').val();
                    var status = $('#filterStatusId').val();
                    var multiSourceFilter = $('#multiSourceFilter').val();
                    var myAddedTasksClicked = $('#myAddedTasksClicked').val();                    
                    var urgentClicked = $('#urgentClicked').val();
                    var orderBy = $("#orderBy").val();
                    var searching = $('#searching').val();
                    var stext="%"+$("#searchBox").val()+"%";
                    var userId = null;
                    
                    var pastDueTasks = false;
                    var dueTodayTasks = false;
                    var urgentTasks = false;
                    var todaysPendingTasks = false;
                    var displayAllUsersTasks = false;
                    var displayAllTasks = $('#displayAllUsersTasks').val();
                    
                    $container = $("#contentContainer");
                    
                    if($container.find("#noRecords").length == 0){
                    	$container.children(".campaign-line").removeClass("active");
                    }
                	$container.append('<div id="imgLoader" style="width: 10%; margin:auto;"><img src="resources/images/ajax-loader-l.gif" alt="loading..." title="loading..."  /></div>');
                	imageLoadCheck = 1;
                    
                    if(catId!="0" || filterUserId!="0" || status !="0" || multiSourceFilter != "false" || myAddedTasksClicked !="false" ){
                    	
                                       	
                    }else{
                    	                    	
                        if(document.URL.indexOf("displayOldTasksPending") > -1){
                        	pastDueTasks = true;
                        }  
                        if(document.URL.indexOf("displayTodaysDueTasks") > -1){
                        	dueTodayTasks = true;
                        }
                        if(document.URL.indexOf("displayUrgentTasks") > -1){
                        	urgentTasks = true;
                        }
                        if(document.URL.indexOf("displayTodaysTasksPending") > -1){
                        	todaysPendingTasks = true;
                        }
                        if(document.URL.indexOf("displayAllMyTasks") > -1){
                        	displayAllUsersTasks = true;
                        }                    
                    	
                    	
                    }
                    
                    
                    
                   if(searching == "true"){
                	   searchFeeds(stext,rowStart,true);   
                   }
                   else if(multiSourceFilter == "true"){
                	    userId = $('#hiddenUserId').val();
                	   multiSourceFilterFeeds(userId, rowStart,true);
                   }else if(followingByCat == "true"){
                	   var followerId = $('#hiddenUserId').val();
                	   var followingCatId = $('#followCatId').val();
                	   followedTasksInCat(followingCatId,followerId,rowStart,true);
                   }else if(urgentClicked == "true"){                	   
                       filterFeeds(catId,filterUserId,status,urgentClicked,rowStart,true,userId,orderBy);                	   
                   }else if(pastDueTasks){
                	   userId = $('#hiddenUserId').val();
                	   displayOldTasksPending(userId,rowStart,true);
                   }else if(dueTodayTasks){
                	   userId = $('#hiddenUserId').val();
                	   displayTodaysDueTasks(userId,rowStart,true);
                   }else if(urgentTasks){
                	   userId = $('#hiddenUserId').val();
                	   displayUrgentTasks(userId,rowStart,true);
                   }else if(todaysPendingTasks){
                	   userId = $('#hiddenUserId').val();
                	   displayTodaysTasksPending(userId,rowStart,true);
                   }else if(displayAllUsersTasks || displayAllTasks == 'true'){
                	   userId = $('#hiddenUserId').val();
                	   displayAllMyTasks(userId,rowStart,true,orderBy);
                   }else if(myAddedTasksClicked == "true"){
                	   userId = $('#hiddenUserId').val();
                       filterFeeds(catId,filterUserId,status,urgentClicked,rowStart,true,userId,orderBy);
                   }else{
                       filterFeeds(catId,filterUserId,status,urgentClicked,rowStart,true,userId,orderBy);
                   }
                    pageStart = pageStart + 1 ;
                    
                }
            }
            } else {
                if (flag1 === 0) {
                    botGrad.show();
                    flag1 = 1;
                }
            }
        });

    }

};


japp.userDropDown = function () {
    var userBtn = jQuery('[data-control=userMenuBtn]'),
        userCreateBtn = jQuery('[data-control=userMenuCreateBtn]'),
        userEditBtn = jQuery('[data-control=userMenuEditBtn]'),
        userCancelBtn = jQuery('[data-control=userDropdownCancel]'),
        userMenu = jQuery('[data-control=userMenu]');

    if (userBtn.size() === 0 && userMenu.size() === 0) {
        return;
    }

    userBtn.live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        if (jQuery(this).is('.active')) {
            japp.userDropDownReset();
            userMenu.removeClass('show').slideUp();
            jQuery(this).removeClass('active');
        } else {
            jQuery(this).addClass('active');
            userMenu.removeClass('hide').slideDown();
        }
    });

    userMenu.bind("clickoutside", function (event) {
        japp.userDropDownReset();
        jQuery(this).removeClass('show').slideUp();
        userBtn.removeClass('active');
    });


    userCreateBtn.live('click', function (e) {
        var userMenuCreate = jQuery('[data-control=userMenuCreate]');
        e.preventDefault();
        e.stopPropagation();
        japp.userDropDownReset(userMenuCreate);
        userMenuCreate.removeClass('hide').addClass('show');
    });

    userEditBtn.live('click', function (e) {
        var userMenuEdit = jQuery('[data-control=userMenuEdit]');
        e.preventDefault();
        e.stopPropagation();
        japp.userDropDownReset(userMenuEdit);
        userMenuEdit.removeClass('hide').addClass('show');
    });

    userCancelBtn.live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        japp.userDropDownReset();
    });
};

japp.userDropDownReset = function (elem) {
    var userMenuChilds = jQuery('[data-control=userMenu] > .user-dropdown-content'),
        element = jQuery(elem);

    if (element.size() !== 0) {
        userMenuChilds.not(element).removeClass('show').addClass('hide');
    } else {
        userMenuChilds.each(function (i) {
            var item = userMenuChilds[i];
            if (i === 0) {
                jQuery(item).removeClass('hide');
            } else {
                jQuery(item).removeClass('show').addClass('hide');
            }
        });
    }
};


japp.helpDropDown = function () {
    var helpBtn = jQuery('[data-control=helpBtn]'),
        helpMenu = jQuery('[data-control=helpMenu]');

    if (helpBtn.size() === 0 && helpMenu.size() === 0) {
        return;
    }

    helpBtn.live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        if (jQuery(this).is('.active')) {
            helpMenu.removeClass('show').slideUp();
            jQuery(this).removeClass('active');
        } else {
            jQuery(this).addClass('active');
            helpMenu.removeClass('hide').slideDown();
        }
    });

    helpMenu.bind("clickoutside", function (event) {
        jQuery(this).removeClass('show').slideUp();
        helpBtn.removeClass('active');
    });
};


//tasks dropdown


japp.tasksDropDown = function () {
    var tasksBtn = jQuery('[data-control=tasksMenuBtn]'),
        tasksMenu = jQuery('[data-control=tasksMenu]');

    if (tasksBtn.size() === 0 && tasksMenu.size() === 0) {
        return;
    }

    tasksBtn.live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        if (jQuery(this).is('.active')) {
            tasksMenu.removeClass('show').slideUp();
            jQuery(this).removeClass('active');
        } else {
            jQuery(this).addClass('active');
            tasksMenu.removeClass('hide').slideDown();
        }
    });

    tasksMenu.bind("clickoutside", function (event) {
        jQuery(this).removeClass('show').slideUp();
        tasksBtn.removeClass('active');
    });
};






japp.campaign = japp.campaign || {};

japp.campaign.showAddForm = function () {
    var btn = jQuery('#toggleAddForm'),
        form = jQuery("#dialogAddTask");

    form.dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        width: 393,
        modal    : true
    });

    if (btn.size() === 0 || form.size() === 0) {
        return;
    }


    btn.on('click', function (e) {
        e.preventDefault();
        form.dialog('open');
    });

    form.find('.cancelBtn').live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        japp.campaign.cancelAddCampaign();
    });

};

japp.campaign.cancelAddCampaign = function () {
    jQuery("#dialogAddTask").dialog('close');
};


japp.campaign.bindButtons = function () {
    japp.campaign.bindReply();
    japp.campaign.moreBind();
    japp.campaign.bindEditCampaign();
    japp.campaign.bindDeleteCampaign();
   // japp.campaign.bindDeleteComment();
    japp.campaign.bindEditComment();
};


japp.campaign.bindReply = function () {
    if (jQuery('[data-control=replyBtn]').size() === 0) {
        return;
    }

    jQuery('[data-control=replyBtn]').each(function() {
        jQuery(this).live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            $('#parentId').val($(this).attr('id'));
            $('#reassignTaskBlock').removeClass('hide');
            
            
            var hiddenArray = $(this).children('input:hidden').map(function() {
                return this.value;
            }).get();
            //console.log(hiddenArray);
            
        	//$('#sourceId').val($(this).children("input").attr('value'));
            var sId = hiddenArray[0],
            assignTo = hiddenArray[1],
            creatorEmailId = hiddenArray[2],
            postCommentEmailNotify = hiddenArray[3],
            assignToName = hiddenArray[4],
            assignerName = hiddenArray[5],
            taskTitle = hiddenArray[6],
            taskAssignerId = hiddenArray[7],
            taskAssignToEmail = hiddenArray[8];
            assignToDept = hiddenArray[9];
            
            $('#sourceId').val(sId);
            $('#assignToId').val(assignTo);
            $('#taskAssignerEmail').val(creatorEmailId);
            $('#replyPostCommentEmailNotify').val(postCommentEmailNotify);
            $('#replyAssignToName').val(assignToName);
            $('#replyAssignerName').val(assignerName);
            $('#replyTaskTitle').val(taskTitle);
            $('#taskAssignerId').val(taskAssignerId);
            $('#taskAssignToEmail').val(taskAssignToEmail);
            $('#assignToDept').val(assignToDept);
            japp.campaign.showReply(jQuery(this));
        });
    });


    jQuery('[data-control=hideReply]').live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        japp.campaign.hideReply();
    });
};


japp.campaign.showReply = function (fThis) {
    var dom = {};
    dom.replyBtn = jQuery(fThis);
    dom.parent = jQuery(dom.replyBtn).parents('[data-control=campaignMore]');
    dom.formContainer = jQuery('[data-control=replyWrapper]', dom.parent);
    dom.replyForm = jQuery('[data-control=replyForm]');

    if (dom.parent.size() === 0 || dom.replyForm.size() === 0) {
        return;
    }

    dom.replyBtn.addClass('hide');
    dom.formContainer.append(dom.replyForm);
    dom.replyForm.removeClass('hide');
    dom.formContainer.removeClass('hide').addClass('show');
};

japp.campaign.hideReply = function() {
    var replyForm = jQuery('[data-control=replyForm]'),
        parent = replyForm.parents('[data-control=replyWrapper]');

    if (parent && parent.hasClass('show')) {
        parent.removeClass('show').addClass('hide');
        jQuery('.hide[data-control=replyBtn]').removeClass('hide');
        replyForm.addClass('hide');
        jQuery(japp.body).append(replyForm);
    }


};




// edit and delete

japp.campaign.bindEditCampaign = function () {
    jQuery("#dialogEditCampaign").dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        width: 393,
        modal    : true
    });

    if (jQuery('[data-control=editCampaign]').size() === 0) {
        return;
    }

    var self = japp;


    jQuery('[data-control=editCampaign]').each(function () {
        var tThis = jQuery(this);
        tThis.live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            self.campaign.startEditCampaign(tThis);
        });
    });

};

japp.campaign.startEditCampaign = function (fThis) {
    var dom = {},
        parentID;
    dom.editBtn = jQuery(fThis);
    dom.parent = jQuery(dom.editBtn).parents('[data-control=campaignWrapper]');
    dom.dialog = jQuery("#dialogEditCampaign");

    //parentID = dom.parent.attr('id');

    dom.dialog.dialog('open');

    dom.dialog.find('.cancelBtn').live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        japp.campaign.cancelEditCampaign();
    });


    // some code

};

japp.campaign.cancelEditCampaign = function () {
    jQuery("#dialogEditCampaign").dialog("close");
};


japp.campaign.bindDeleteCampaign = function () {
    jQuery("#dialogDel").dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        height   : 140,
        modal    : true,
        buttons  : {
            "Delete": function () {
                jQuery(this).dialog("close");
                
                
                deleteId = $("#deletedId").val();
                deleteFeed(deleteId);
                
                //alert("here");
                // TODO: del code
            },
            Cancel: function () {
                jQuery(this).dialog("close");
            }
        }
    });

    if (jQuery('[data-control=delCampaign]').size() === 0) {
        return;
    }

    var self = japp;




    jQuery('[data-control=msgBox]').each(function () {
        var fThis = jQuery(this);
        jQuery('[data-control=delCampaign]', fThis).each(function () {
            var tThis = jQuery(this);
            tThis.live('click', function (e) {
                e.preventDefault();
                e.stopPropagation();
                self.campaign.deleteCampaign(tThis);
            });
        });
    });
};

japp.campaign.deleteCampaign = function (fThis) {
    var dom = {};
    dom.delBtn = jQuery(fThis);
    dom.parent = jQuery(dom.delBtn).parents('[data-control=campaignWrapper]');
    val = dom.delBtn.find('input[type=hidden]').val();
   // alert(val);
    //jQuery("#dialogDel p").remove();
    jQuery('#deletedId').remove();
    jQuery("#dialogDel").dialog('open');
    jQuery("#dialogDel").append('<input type="hidden" id="deletedId" value='+val+'>');
};


japp.campaign.bindEditComment = function () {
    jQuery("#dialogEditComment").dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        width: 375,
        modal    : true
    });


    if (jQuery('[data-control=editComment]').size() === 0) {
        return;
    }

    var self = japp;



    jQuery('[data-control=commentsBox]').each(function () {
        var fThis = jQuery(this);
        jQuery('[data-control=editComment]', fThis).each(function () {
            var tThis = jQuery(this);
            tThis.live('click', function (e) {
                e.preventDefault();
                e.stopPropagation();
                self.campaign.startEditComment(tThis);
            });
        });
    });
};


japp.campaign.startEditComment = function (fThis) {
    var dom = {},
        parentID;
    dom.editBtn = jQuery(fThis);
    dom.parent = jQuery(dom.editBtn).parents('.campaign-comment');

    //parentID = dom.parent.attr('id');

    jQuery("#dialogEditComment").dialog('open');
    // some code

};

japp.campaign.cancelEditComment = function () {
    jQuery("#dialogEditComment").dialog("close");
};


// add region dialog

japp.bindAddRegion = function () {
    var btn = jQuery('[data-control=userMenuRegion]'),
        dialog = jQuery("#dialogAddRegion");

    dialog.dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        width: 490,
        modal    : true
    });


    if (btn.size() === 0 || dialog.size() === 0) {
        return;
    }


    btn.on('click', function (e) {
        e.preventDefault();
        dialog.dialog('open');
    });

    dialog.find('.cancelBtn').live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        dialog.dialog('close');
    });

};


japp.addRegions = function () {

    if (jQuery('[data-control=regionAddField]').size() === 0) {
        return false;
    }
    var addWrapper = jQuery('[data-control=regionAddField]'),
        addField = jQuery('input[type=text]', addWrapper),
        addReset = jQuery('input[type=reset]', addWrapper),
        addBtn = jQuery('button', addWrapper),
        tdContainsRegion,
        rId;
    
    
    // Added by Harjinder 
    jQuery('[data-control=regionEdit]').live("click", function(e) {
        e.preventDefault();
        e.stopPropagation();
        var self = $(this);  
        rId = self.parent().find('input:hidden').val();
        tdContainsRegion = self.closest('tr').children('td:first');
        addField = self.parents('.dialog-region-content').find('input[type=text]'),
        regionName = tdContainsRegion.text();        
        
        addField.val(regionName);
        addBtn.text("edit");
        
    });
    
    addReset.click(function() {
    	addBtn.text("add");
    });

    addBtn.click(function () {
    	var objAdd, templateName, tableName;
    	var self = $(this),
    	addField = self.prev('input[type=text]'),
    	list = self.parents('.dialog-region-content').find('[data-control=regionEditList]'),
    	action = self.closest("form").attr("action"),
    	textBoxVal = addField.val();
    	if(action == 'addDept'){
    		objAdd = {name:textBoxVal};
    		templateName = "displayDept";
    		tableName = "deptTable";
    	}else{
    		objAdd = {name:textBoxVal};
    		templateName = "displayRegions";
    		tableName = "regionTable";
    	}
    	objAdd =  (action == 'addDept')?{name:textBoxVal}:{regionName:textBoxVal};
    	objEdit =  (action == 'addDept')?{id:rId,name:textBoxVal}:{id:rId,regionName:textBoxVal};
        if (self.text() == 'add' && $.trim(textBoxVal) !== '') {
            $.post(action, objAdd,
    				function(result) {
            	var addRegionOrDept =  (action == 'addDept')?[{id:result,name:textBoxVal}]:[{id:result,regionName:textBoxVal}];
            	if(result != 0){
            		var theTemplate = render(templateName, addRegionOrDept);
            		$("#"+tableName).append(theTemplate);
            	}
    					
    		   });
            
            addField.val('');
        
        }
        
        if (self.text() == 'edit' && $.trim(addField.val()) !== '') {
        	editAction =  (action == 'addDept')?'editDept':'editRegion';
            $.post(editAction, objEdit,
    				function(result) {
    					console.log(result);
    		   });
            tdContainsRegion.text(addField.val());
            addField.val('');
            addBtn.text("add");
        
        }
    });


    /*jQuery('[data-control=delRegion]').live("click", function(e) {
    	rId = jQuery(this).parent().find('input:hidden').val();
        e.preventDefault();
        e.stopPropagation();
        $.post('deleteRegion', {
        	id:rId
				},
				function(result) {
					if(result != 0){
						//japp.users.destroyRegion(tThis);
						e.preventDefault();
			            e.stopPropagation();
			            japp.users.destroyRegion(tThis);
					}
		   });
        jQuery(this).parents('tr').remove();
    });*/

};




// show more

japp.campaign.moreBind = function () {
    if (jQuery('[data-control=campaignShowMore]').size() === 0) {
        return;
    }

    jQuery('[data-control=campaignShowMore]').each(function () {
        jQuery(this).find('a').live('click', function (e) {
            e.preventDefault();
        });
        jQuery(this).live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            viewerId = $('#hiddenUserId').val();
            catId = jQuery(this).find('input[type=hidden]').val();
            updateRecentlyViewed(viewerId, catId);
            japp.campaign.moreShow(jQuery(this));
        });
    });
};

japp.campaign.moreShow = function (fThis) {
    var dom = {};
    dom.moreBtn = jQuery(fThis);
    dom.parent = dom.moreBtn.parents('[data-control=campaignWrapper]');

    japp.campaign.moreShowReset(dom.parent);
    dom.parent.addClass('active');
};

japp.campaign.moreShowReset = function (activeCampaign) {
    var campaign = jQuery(activeCampaign);
    japp.campaign.hideReply();
    campaign.siblings('.active').removeClass('active');
};

japp.campaign.bindEditLeftNav = function () {
    var editBtn = jQuery('[data-control=editLeftNav]');

    if (editBtn.size() === 0) {
        return;
    }

    editBtn.live('click', function () {
        if (jQuery(this).is('.active')) {
            japp.campaign.hideEditLeftNav(jQuery(this));
        } else {
            japp.campaign.showEditLeftNav(jQuery(this));
        }
    });
};

japp.campaign.showEditLeftNav = function (fThis) {
    var editBtn = fThis,
        dom = {};
    dom.wrapper = jQuery('[data-control=leftNavFolders]');
    dom.triggers = jQuery('.edit-row', dom.wrapper);
    
    if (!dom.wrapper.is(':visible')) {
        return;
    }


    //editBtn.addClass('active').append('<span>Save</span>');

    dom.wrapper.addClass('edit');

    dom.triggers.each(function () {
        jQuery(this).live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            japp.campaign.bindLeftNavTriggers(jQuery(this));
        });
    });

};

//Edit subtopics



japp.bindEditSubtopics = function () {

    if (jQuery('[data-control=subtopicAddField]').size() === 0) {
        return false;
    }

    var addWrapper = jQuery('[data-control=subtopicAddField]'),
        addField = jQuery('input[type=text]', addWrapper),
        addBtn = jQuery('.btn-control', addWrapper),
        list = jQuery('[data-control=subtopicsEditList]');

    addBtn.click(function () {
        list.append('<li><i class="icon-ball"><i style="background: #ffd200;"></i></i> <a id = "subtopicValues" href="#">' + addField.val() + '</a><span class="edit-row"><a href="#" data-control="delSubtopics"></a></span></li>');
        subtopicArray.push(addField.val());
        //alert(subtopicArray);
        addField.val('');
    });


    jQuery('[data-control=delSubtopics]').live("click", function(e) {
        e.preventDefault();
        e.stopPropagation();
        delList = jQuery(this).parents('li');
       // alert(list.children("a").text());
        
       removeItem = delList.children("a").text();
        
       subtopicArray = jQuery.grep(subtopicArray, function(value) {
            return value != removeItem;
          });
        
        jQuery(this).parents('li').remove();
        //alert(subtopicArray);
    });

};



japp.campaign.bindLeftNavTriggers = function (fThis) {
    var toggleBtn = fThis,
        parent = jQuery(toggleBtn).parents('li');

    if (parent.hasClass('gray')) {
        parent.removeClass('gray');
        toggleBtn.html('Show');
       showHideTopic(parent.children("input").attr('value'),$('#hiddenUserId').val(),"show");
       
    } else {
        parent.addClass('gray');
        toggleBtn.html('Hide');
        showHideTopic(parent.children("input").attr('value'),$('#hiddenUserId').val(),"hide");
    }
};

japp.campaign.hideEditLeftNav = function (fThis) {
    var editBtn = fThis,
        dom = {};
    dom.wrapper = jQuery('[data-control=leftNavFolders]');
    dom.triggers = jQuery('.edit-row', dom.wrapper);

    editBtn.removeClass('active').find('span').remove();
    dom.wrapper.removeClass('edit');

    dom.triggers.each(function () {
        jQuery(this).unbind('click');
    });
};

japp.alertBox = function () {
    var alertWrapper = jQuery('[data-control=alert]');
    if (jQuery('[data-control=alert]').size() === 0) {
        return;
    }

    alertWrapper.each(function () {
        jQuery(this).find('[data-control=alertClose]').click(japp.alertBoxClose);
    });
};

japp.alertBoxClose = function (e) {
    var parent = jQuery(this).parent('[data-control=alert]');
    parent.hide();
};


japp.users = japp.users || {};

japp.users.userItemsFixMargin = function() {
    $(".preferences.user-item-wrapper:nth-child(3n+1)").css("margin-right", "0").find(".user-details").css("left", "-52px");  
};

japp.users.addFormBind = function () {
    if (jQuery('[data-control=addUserBtn]').size() === 0) {
        return;
    }

    var self = japp;

    jQuery('[data-control=addUserBtn]').each(function() {
        jQuery(this).live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            if(jQuery(this).is('.active')){
                jQuery(this).removeClass('active');
                jQuery('[data-control=addUserForm]').removeClass('show').addClass('hide');
            } else {
                jQuery(this).addClass('active');
                jQuery('[data-control=addUserForm]').removeClass('hide').addClass('show');
            }
        });

    });

};


	
$('#usersMgmtDiv').on('click', '[data-control=userBtn]', function(e) {
    e.preventDefault();
    e.stopPropagation();
    if(jQuery(this).is('.active')){
    	japp.users.hideUserBox(jQuery(this));
    } else {
    	japp.users.showUserBox(jQuery(this));
    }
});	

$('#usersMgmtDiv').on('click','[data-control=userInfoSaveButton]',function(e){
    e.preventDefault();
    e.stopPropagation();
    
    var dom = {};
    
    var formId = $(this).closest('form').attr('id');
    
    modifyUserDept(formId);
    
    dom.userBtn = $(this).parents().find('[data-control=userBtn]');
    japp.users.hideUserBox(dom.userBtn);
   
});

japp.users.bindEdit = function () {
   if (jQuery('[data-control=userBtn]').size() === 0) {
        return;
    }
   
   

   var self = japp;	

  //  jQuery('[data-control=userBtn]').each(function() {
    /* $('#usersMgmtDiv').on('click', '[data-control=userBtn]', function(e) {
        	console.log(jQuery(this));
            e.preventDefault();
            e.stopPropagation();
            if(jQuery(this).is('.active')){
                self.users.hideUserBox(jQuery(this));
            } else {
                self.users.showUserBox(jQuery(this));
            }*/
       // });
     //return false;
   // });
    
    /*jQuery('[data-control=userInfoSaveButton]').each(function() {
        jQuery(this).live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            
            var dom = {};
            
            var formId = $(this).closest('form').attr('id');
            
            modifyUserDept(formId);
            
            dom.userBtn = $(this).parents().find('[data-control=userBtn]');
            self.users.hideUserBox(dom.userBtn);
           
        });
    }); */

    jQuery('[data-control=userInfoCancelButton]').each(function() {
        jQuery(this).live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            
            var dom = {};
            
            dom.userBtn = $(this).parents().find('[data-control=userBtn]');
            self.users.hideUserBox(dom.userBtn);
           
        });
    }); 
  

    
};

japp.users.bindUserInfoBox = function () {
	if (jQuery('[data-control=userDetails]').size() === 0) {
        return;
    }
	 var self = japp;
	jQuery('[data-control=userDetails]').each(function() {
		var userInfoBox = jQuery(this);
		userInfoBox.live('clickoutside', function (e) {
            e.preventDefault();
            e.stopPropagation();
            self.users.hideUserBox(userInfoBox);
        });
    });
}


japp.users.hideUserInfoBo = function (fThis) {
	
	var dom = {};
    dom.userInfoBox = jQuery(fThis);
    dom.wrapper = jQuery(dom.userInfoBox).parents('[data-control=userItemWrapper]');
    dom.userBtn = jQuery(dom.wrapper).find('[data-control=userBtn]');
    dom.userBtnIcon = jQuery('i', dom.userBtn);
    dom.userBtn.removeClass('active');
    dom.userBtnIcon.removeClass('up');
    
    dom.userInfoBox.addClass('hide').removeClass('show');
    dom.userInfoBox.removeClass('open');
	
}


japp.users.showUserBox = function (fThis) {
    var dom = {},
        height;
    dom.userBtn = jQuery(fThis);
    dom.userBtnIcon = jQuery('i', dom.userBtn);
    dom.wrapper = jQuery(dom.userBtn).parents('[data-control=userItemWrapper]');
    dom.dropDown = jQuery(dom.wrapper).find('[data-control=userDetails]');
    dom.userBtnParent = jQuery(dom.userBtn).parent();

    japp.users.resetUserBox();

    dom.userBtn.addClass('active');
    dom.userBtnIcon.addClass('up');
    dom.wrapper.addClass('active');
    dom.dropDown.addClass('show').removeClass('hide');

//    height = dom.parent.height();
//    dom.userBtnParent.height(height);
//    dom.userBtn.height(height);

};

japp.users.hideUserBox = function (fThis) {
    var dom = {};
    dom.userBtn = jQuery(fThis);
    dom.userBtnIcon = jQuery('i', dom.userBtn);
    dom.wrapper = jQuery(dom.userBtn).parents('[data-control=userItemWrapper]');
    dom.dropDown = jQuery(dom.wrapper).find('[data-control=userDetails]');
    dom.userBtnParent = jQuery(dom.userBtn).parent();

    dom.userBtn.removeClass('active');
    dom.userBtnIcon.removeClass('up');
    dom.dropDown.addClass('hide').removeClass('show');
    dom.wrapper.removeClass('open');

//    dom.userBtnParent.height('');
//    dom.userBtn.height('');
};



japp.users.resetUserBox = function () {
    jQuery('.active[data-control=userBtn]').click();
};



japp.users.sortBind = function () {
    if (jQuery('[data-control=sortBtns]').size() === 0) {
        return;
    }

    jQuery('[data-control=sortBtns]').buttonset();
};

japp.users.sourceBind = function () {
    if (jQuery('[data-control=userCheckBox]').size() === 0) {
        return;
    }

    jQuery('[data-control=userCheckBox]').each(function () {
        jQuery(this).live('click', function () {
            if (jQuery(this).is(':checked')) {
                japp.users.sourceMoveSeen(jQuery(this).parents('[data-control=userItemSource]'));
                //alert(jQuery(this).attr("id"));
            } else {
                japp.users.sourceMoveAll(jQuery(this).parents('[data-control=userItemSource]'));
            }
        });
    });
};

japp.users.sourceMoveSeen = function (item) {
    var parent = jQuery('[data-control=userSeen]');
    parent.append(item);

};

japp.users.sourceMoveAll = function (item) {
    var parent = jQuery('[data-control=userAll]');
    parent.append(item);
};


japp.users.sourceSelectAll = function () {
    if (jQuery('[data-control=userSelectAll]').size() === 0) {
        return;
    }


    jQuery('[data-control=userSelectAll]').live('click', function () {
        var checkboxArray;
        if (jQuery(this).is(':checked')) {
            checkboxArray = jQuery('[data-control=userCheckBox]', jQuery('[data-control=userAll]'));
            checkboxArray.each(function () {
                jQuery(this).attr('checked', 'checked');
                japp.users.sourceMoveSeen(jQuery(this).parents('[data-control=userItemSource]'));
            });
        } else {
            checkboxArray = jQuery('[data-control=userCheckBox]', jQuery('[data-control=userSeen]'));
            checkboxArray.each(function () {
                jQuery(this).removeAttr('checked');
                japp.users.sourceMoveAll(jQuery(this).parents('[data-control=userItemSource]'));
            });
        }
    });

};


$('#usersMgmtDiv').on('click', '.user-add-permissions', function(e) {
	        event.preventDefault();
	        var nextDiv = $(this).next('div');
	         nextDiv.fadeToggle('fast');
	        return false;
 });


jQuery('[data-control=deptDropdown]').on("clickoutside", function() {
    $(this).fadeOut('fast');
});

    
$(".user-add-permissions-options .user-permission-option").on("change", function() {
    if ($(this).is(":checked")) {
        $(this).next().addClass("activePermission");
    } else {
        $(this).next().removeClass("activePermission");
    }
});

$('#usersMgmtDiv').bind("clickoutside", function (event) {
	jQuery('[data-control=deptDropdown]').fadeOut('fast');
	//userInfoBox = jQuery('[data-control=userDetails]');
	userInfoBox = $(this).parents().find('[data-control=userDetails]');
    //japp.users.hideUserBox(dom.userBtn);
	japp.users.hideUserBox(userInfoBox);
   // tasksBtn.removeClass('active');
});

japp.users.permissionsDropdown = function () {
    $(".user-add-permissions").on("click", function(event) {
        event.preventDefault();
        var nextDiv = $(this).next('div');
         nextDiv.fadeToggle('fast');
        return false;
    });
    

    
    jQuery('[data-control=topicsDropdown]').on("clickoutside", function() {
        $(this).fadeOut('fast');
    });
    
    jQuery('[data-control=deptDropdown]').on("clickoutside", function() {
        $(this).fadeOut('fast');
    });
    
        
    $(".user-add-permissions-options .user-permission-option").on("change", function() {
        if ($(this).is(":checked")) {
            $(this).next().addClass("activePermission");
        } else {
            $(this).next().removeClass("activePermission");
        }
    });
};




//
japp.users.bindDestroySource = function () {
    jQuery("#dialogDelSource").dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        height   : 140,
        modal    : true,
        buttons  : {
            Delete: function () {
                jQuery(this).dialog("close");
                deleteId = $("#deleteSourceId").val();
                deleteSource(deleteId);
            },
            Cancel: function () {
                jQuery(this).dialog("close");
            }
        }
    });

    if (jQuery('[data-control=sourceDestroy]').size() === 0) {
        return;
    }

    var self = japp;

    jQuery('[data-control=sourceDestroy]').each(function () {
        var tThis = jQuery(this);
        tThis.live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            self.users.destroySource(tThis);
        });
    });
};



japp.users.destroySource = function (fThis) {
	 var dom = {};
	    dom.delBtn = jQuery(fThis);
	    dom.parent = jQuery(dom.delBtn).parents('[data-control=sourceDestroy]');
	    val = dom.delBtn.find('input[type=hidden]').val();
	    jQuery('#deleteSourceId').remove();
	    
    jQuery("#dialogDelSource").dialog('open');
    jQuery("#dialogDelSource").append('<input type="hidden" id="deleteSourceId" value='+val+'>');
};

//Dialog message

japp.users.bindDestroyRegion = function () {
    jQuery("#dialogDelRegion").dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        height   : 140,
        modal    : true,
        buttons  : {
            Ok: function () {
                jQuery(this).dialog("close");
            }
        }
    });

   /* if (jQuery('[data-control=delRegion]').size() === 0) {
        return;
    }
*/
    var self = japp;

    jQuery('[data-control=delRegion]').live("click", function(e) {
        e.preventDefault();
        e.stopPropagation();
        var rthis = jQuery($(this));
        action = rthis.closest("form").attr("action");
        deleteAction =  (action == 'addDept')?'deleteDept':'deleteRegion';
        	rId = rthis.parent().find('input:hidden').val();
        	
           
            $.post(deleteAction, {
            	id:rId
    				},
    				function(result) {
    					console.log(result);
    					if(result != 0){
    						//japp.users.destroyRegion(tThis);
    						e.preventDefault();
    			            e.stopPropagation();
    			            self.users.destroyRegion(rthis);
    					}else{
    						rthis.parents('tr').remove();
    					}
    		   });
            
        });
   
};




japp.users.destroyRegion = function (fThis) {
    
    jQuery("#dialogDelRegion").dialog('open');
};


//

japp.users.bindDestroyUser = function () {
    jQuery("#dialogDelUser").dialog({
        autoOpen: false,
        resizable: false,
        show: "fade",
        hide: "explode",
        height   : 140,
        modal    : true,
        buttons  : {
            Delete: function () {
                jQuery(this).dialog("close");
                deleteId = $("#deleteUserId").val();
                deleteUser(deleteId);
            },
            Cancel: function () {
                jQuery(this).dialog("close");
            }
        }
    });

    if (jQuery('[data-control=userDestroy]').size() === 0) {
        return;
    }

    var self = japp;

    jQuery('[data-control=userDestroy]').each(function () {
        var tThis = jQuery(this);
        tThis.live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            self.users.destroyUser(tThis);
        });
    });
};

japp.users.destroyUser = function (fThis) {
	var dom = {};
    dom.delBtn = jQuery(fThis);
    dom.parent = jQuery(dom.delBtn).parents('[data-control=userDestroy]');
    val = dom.delBtn.find('input[type=hidden]').val();
    jQuery('#deleteUserId').remove();
    
    jQuery("#dialogDelUser").dialog('open');
    jQuery("#dialogDelUser").append('<input type="hidden" id="deleteUserId" value='+val+'>');
};


/**
 *
 */


japp.datePicker = function () {
    if (jQuery('[data-control=datepicker]').size() === 0) {
        return;
    }

    jQuery('[data-control=datepicker]').each(function() {
        var from = jQuery('[data-control=datepickerFrom]', this),
            to = jQuery('[data-control=datepickerTo]', this);

        if (to.size() === 0) {
            from.datepicker({
                                numberOfMonths: 1
                            });
        } else {
            from.datepicker({
                                numberOfMonths: 1,
                                onSelect      : function (selectedDate) {
                                    to.datepicker("option", "minDate", selectedDate);
                                }
                            });
            to.datepicker({
                              numberOfMonths: 1,
                              onSelect      : function (selectedDate) {
                                  from.datepicker("option", "maxDate", selectedDate);
                              }
                          });
        }

    });
};

japp.placeholder = function () {
    jQuery(".ie7 input[type='text'], .ie8 input[type='text'], .ie9 input[type='text']").each(
        function() {
            if (jQuery(this).val() == "" && jQuery(this).attr("placeholder") != "") {
                jQuery(this).val(jQuery(this).attr("placeholder"));
                jQuery(this).focus(function() {
                    if (jQuery(this).val() == jQuery(this).attr("placeholder")) jQuery(this).val("");
                });
                jQuery(this).blur(function() {
                    if (jQuery(this).val() == "") jQuery(this).val(jQuery(this).attr("placeholder"));
                });
            }
        }
    );
};

japp.accordionBind = function () {
    var btn = jQuery('[data-control=leftExpander]');

    if (btn.size() === 0) {
        return;
    }

    btn.live('click', function () {
        if (jQuery(this).is('.collapsed')) {
            japp.accordionShow(jQuery(this));
        } else {
            japp.accordionHide(jQuery(this));
        }
    });
};

japp.accordionShow = function (fThis) {
    jQuery(fThis).parent().next('.left-nav-sub').removeClass('accordion-hide').slideDown();

    jQuery(fThis).addClass('down').removeClass('collapsed');
};

japp.accordionHide = function (fThis) {
	jQuery(fThis).parent().next('.left-nav-sub').slideUp();
    jQuery(fThis).removeClass('down').addClass('collapsed');
};

japp.accordionItemBind = function () {
    var btn = jQuery('[data-control=dropdownExpander]');

    if (btn.size() === 0) {
        return;
    }

    btn.live('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        if (jQuery(this).is('.collapsed')) {
            japp.accordionItemShow(jQuery(this));
        } else {
            japp.accordionItemHide(jQuery(this));
        }
    });
};

japp.accordionItemShow = function (fThis) {
    jQuery(fThis).siblings('.left-nav-dropdown').removeClass('accordion-hide').slideDown();
    jQuery(fThis).addClass('down').removeClass('collapsed');
};

japp.accordionItemHide = function (fThis) {
    jQuery(fThis).siblings('.left-nav-dropdown').slideUp();
    jQuery(fThis).removeClass('down').addClass('collapsed');
};




japp.status = function () {
    var btn = jQuery('[data-control=statusItem]'),
        container = jQuery('[data-control=statusCurrent]');

    if (btn.size() === 0) {
        return;
    }

    btn.each(function () {
        jQuery(this).live('click', function () {
            jQuery(this).siblings('.status-item').removeClass('active');
            jQuery(this).addClass('active');
            container.html('');
            jQuery(this).clone().appendTo(container);
            $('#replyStatus').val(jQuery(this).attr('id'));
            $('#replyStatus').data("replyStatus",jQuery(this).text());
            if(jQuery(this).attr('id') == 4){
            	$('#reassignTaskBlock').addClass('hide');
            }else{
            	$('#reassignTaskBlock').removeClass('hide');
            }
        });
    });
};

japp.ajaxLoadingShow = function (btn) {
    jQuery(btn).addClass('ajax-loading');
};

japp.ajaxLoadingHide = function (btn) {
    jQuery(btn).removeClass('ajax-loading');
};

japp.colorPicker = function () {
    if (jQuery('[data-control=editColorBtn]').size() === 0) {
        return;
    }

    jQuery('#editColorPicker').farbtastic('#editColor');
    jQuery('#editColorPicker').hide();

    jQuery('[data-control=editColorBtn]').each(function() {
        jQuery(this).live('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            if(jQuery(this).is('.active')){
                jQuery('#editColorPicker').hide();
                jQuery(this).removeClass('active');
            } else {
                jQuery('#editColorPicker').show();
                jQuery(this).addClass('active');
            }
        });

    });
};

japp.pref = japp.pref || {};

japp.pref.tabs = function () {
    $( "#tabsPreferences" ).tabs();
    $( "#tabsPreferences li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
};


japp.autocompleteInput = function() {

    $.widget("custom.catcomplete", $.ui.autocomplete, {
        _renderMenu: function (ul, items) {
            var that = this, currentCategory = "";
            $.each(items, function (index, item) {
                if (item.category != currentCategory) {
                    ul.append("<li class='ui-autocomplete-category'>" + item.category + "</li>");
                    currentCategory = item.category;
                }
                that._renderItem(ul, item);
            });
            ul.append("<span class='arrowForAutocompleteBlock'>");
        }
    });
    
    
 $(".autocompleteContainer").each(function() {
        
        var optionTexts = [];
        var atocompleteContainer = $(this);
        var crutchStatus = $(this).parent().hasClass("autocompleteCrutch");
        
        
        atocompleteContainer.find(".autocompleteGroupContainer").each(function() {
            var autocompleteGroupContainer = $(this);
            var optionTextsGroup = autocompleteGroupContainer.find(".autocompleteGroupTitle").text();
            autocompleteGroupContainer.find(".autocompleteItem").each(function() {
                optionTexts.push({ label: $(this).text(),
                                   itemId: $(this).attr("data-item-id"),
                                   category: optionTextsGroup}); 
            });
        });

        if ( crutchStatus ) {
            $("#" + atocompleteContainer.attr("data-autocomplete-target")).catcomplete({
                appendTo: "#" + atocompleteContainer.parents(".autocompleteCrutch").attr("id"),
                delay: 0,
                source: optionTexts,
                select: function(event, ui) {$("#" + atocompleteContainer.attr("data-autocomplete-target")).attr("data-selected-item-id", ui.item.itemId);}
            });
        } else {
            $("#" + atocompleteContainer.attr("data-autocomplete-target")).catcomplete({
                delay: 0,
                source: optionTexts,
                select: function(event, ui) {$("#" + atocompleteContainer.attr("data-autocomplete-target")).attr("data-selected-item-id", ui.item.itemId);}
            });
        }
        
    });   
   
};  


japp.init = function () {
    var self = japp;

    init();
   // initHighrise();
    self.layout.colsHeight();
    self.layout.scrollShadow();
    self.placeholder();
    self.selectBoxes();
    self.userDropDown();
    self.helpDropDown();
    self.campaign.showAddForm();
    self.datePicker();
    self.alertBox();
    self.accordionBind();
    self.accordionItemBind();
    self.users.userItemsFixMargin();
    self.users.permissionsDropdown();
    self.users.sortBind();
    self.users.bindEdit();
    self.users.bindUserInfoBox();
    self.users.sourceBind();
    self.users.sourceSelectAll();
    self.users.bindDestroySource();
    self.users.bindDestroyRegion();
    self.campaign.bindEditCampaign();
    self.campaign.bindDeleteCampaign();
    self.users.bindDestroyUser();
    self.campaign.bindEditComment();
    self.campaign.moreBind();
    self.campaign.bindReply();
    self.campaign.bindEditLeftNav();
    self.status();
    self.colorPicker();
    self.tasksDropDown();
    self.bindEditSubtopics();
    self.bindAddRegion();
    self.addRegions();
    self.autocompleteInput();
    self.pref.tabs();
    self.users.addFormBind();
    
    $("#groupsTable").tablesorter();

};

jQuery(window).resize(japp.layout.colsHeight);


jQuery(document).ready(japp.init);

