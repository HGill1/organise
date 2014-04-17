<!-- <div id="tabs-7"> -->
                                <div class="form-inline locationsGroupsContainer clearFix">
                                    <form action="addRegion">
                                    <input type="hidden" id="formSubmitted" name ="formClicked" value="addRegion"> 
                                        <div class="form-inline-header">Locations and offices</div>
                                        <div class="form-inline-content">
                                            <div class="dialog-region-content">
                                            
                                                <div class="form-line-inline" data-control="regionAddField">
                                                    <div class="label"><label>New location</label></div>
                                                    <input class="field xsmall" type="text" placeholder="Write here office / location name">
                                                    <button class="btn-small" type="button">add</button>
                                                    <input class="btn-small" type="reset" value="cancel">
                                                </div>

                                                <div class="label"><label>Exist locations</label></div>

                                                <table class="source-table dialog" data-control="regionEditList">
                                                    <tr class="source-header">
                                                        <th>Region name</th>
                                                        <th style="width: 104px">Action</th>
                                                    </tr>
                                                    <%-- <c:forEach var="region" items="${regions}">
                                                    <tr>
                                                        <td class="regionName">${region.regionName}</td>
                                                        <td class="region-edit-btns" data-control="region-edit-del">
                                                        <input type="hidden" name='region-id' id="region-id" class='region-id' value ="${region.id}"/>
                                                            <a href="#" data-control="regionEdit">Edit</a><a href="#" data-control="delRegion">Delete</a>
                                                        </td>
                                                    </tr>
                                                    </c:forEach> --%>
                                                </table>
                                            </div>

                                        </div>
                                        <div class="form-inline-footer">
                                           <!--  <button type="submit" class="btn-control create"><span>Save</span></button>
                                            <a class="cancelBtn" href="#">CANCEL</a> -->
                                        </div>
                                    </form>
                                </div>
                                
                                  <script id="regions-template" type="x-handlebars-template">
									{{#each this}}
									<tr>
                                        <td class="regionName">{{regionName}}</td>
                                        <td class="region-edit-btns" data-control="region-edit-del">
                                          <input type="hidden" name='region-id' id="region-id" class='region-id' value ="{{id}}"/>
                                               <a href="#" data-control="regionEdit">Edit</a><a href="#" data-control="delRegion">Delete</a>
                                        </td>
									</tr>
									{{/each}}
                       			   </script>   
                                
                                
                                <div class="form-inline locationsGroupsContainer clearFix">
                                    <form action="addDept">
                                    <input type="hidden" id="formSubmitted" name ="formClicked" value="addDept">
                                        <div class="form-inline-header">Groups</div>
                                        <div class="form-inline-content">
                                            <div class="dialog-region-content">
                                                <div class="form-line-inline" data-control="regionAddField">
                                                    <div class="label"><label>New group</label></div>
                                                    <input class="field xsmall" type="text" placeholder="Group name">
                                                    <button class="btn-small" type="button">add</button>
                                                    <input class="btn-small" type="reset" value="cancel">
                                                </div>

                                                <div class="label"><label>Exist groups</label></div>

                                                <table class="source-table dialog" data-control="regionEditList">
                                                    <tr class="source-header">
                                                        <th>Name</th>
                                                        <th style="width: 104px">Action</th>
                                                    </tr>
                                                    <c:forEach var="dept" items="${allDepts}">
                                                    <tr>
                                                        <td class="regionName">${dept.name}</td>
                                                        <td class="region-edit-btns" data-control="region-edit-del">
                                                        <input type="hidden" name='region-id' id="region-id" class='region-id' value ="${dept.id}"/>
                                                            <a href="#" data-control="regionEdit">Edit</a><a href="#" data-control="delRegion">Delete</a>
                                                        </td>
                                                    </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>                                        
                                    </form>
                                </div>
                                
                                 <script id="dept-template" type="x-handlebars-template">
									{{#each this}}
									<tr>
                                        <td class="regionName">{{deptName}}</td>
                                        <td class="region-edit-btns" data-control="region-edit-del">
                                          <input type="hidden" name='region-id' id="region-id" class='region-id' value ="{{id}}"/>
                                               <a href="#" data-control="regionEdit">Edit</a><a href="#" data-control="delRegion">Delete</a>
                                        </td>
									</tr>
									{{/each}}
                       			   </script> 
                                
                                
                           <!--  </div> -->
                      
                           