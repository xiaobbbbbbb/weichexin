/*************************************************************************************************
 * @author zhanglu
 * 	该js分两部分:
 * 1，utils工具模块 (@util代表工具模块）
 * 2，业务功能模块 (@module代表功能模块）
 **************************************************************************************************/

/**
 * ===============================================================================================
 * @util 文本框文本域提示文字的自动显示与隐藏
 * ===============================================================================================
 */
(function($) {
	$.fn.textRemindAuto = function(options) {
		options = options || {};
		var defaults = {
			blurColor : "#999",
			focusColor : "#ffffff",
			auto : true,
			chgClass : ""
		};
		var settings = $.extend(defaults, options);
		$(this).each(function() {
			if (defaults.auto) {
				$(this).css("color", settings.blurColor);
			}
			var v = $.trim($(this).val());
			if (v) {
				$(this).focus(function() {
					if ($.trim($(this).val()) === v) {
						$(this).val("");
					}
					$(this).css("color", settings.focusColor);
					if (settings.chgClass) {
						$(this).toggleClass(settings.chgClass);
					}
				}).blur(function() {
					if ($.trim($(this).val()) === "") {
						$(this).val(v);
					}
					$(this).css("color", settings.blurColor);
					if (settings.chgClass) {
						$(this).toggleClass(settings.chgClass);
					}
				});
			}
		});
	};
})(jQuery);

/**
 * ===============================================================================================
 * @util  datepicker 时间控件中文化
 * ===============================================================================================
 */
function localizeDatepicker() {
	$.datepicker.regional['zh-CN'] = {
		closeText : '关闭',
		prevText : '<上月',
		nextText : '下月>',
		currentText : '今天',
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
				'十月', '十一月', '十二月' ],
		monthNamesShort : [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十',
				'十一', '十二' ],
		dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		weekHeader : '周',
		dateFormat : 'yy-mm-dd',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : true,
		yearSuffix : '年'
	};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
}

/**
 * ===============================================================================================
 * @util 表单模块
 * ===============================================================================================
 */
var FormUtils = (function() {
	return {
		checkLength: function (str, minLen, maxLen) {
			 var len = str.length;
			 if (len>=minLen && len<=maxLen) {
				 return true;
			 } else {
				 return false;
			 }
		},
		checkLengthNotIgnoreCh: function (str, minLen, maxLen) {
			 var len = str.replace(/[^\x00-\xff]/g,"**").length;
			 alert(len);
			 if (len>=2*minLen && len<=2*maxLen) {
				 return true;
			 } else {
				 return false;
			 }
		},
		isPhoneNoEx: function(str) {
			var reg = /^\d(\d{3,11})\d$/;
			return reg.test(str);
		},
		isMobilePhone: function(str) {
			var reg = /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/;
			return reg.test(str);
		},
		isTelPhone: function (str) {
			var reg = /^\d(\d{9}|\d{10})\d$/;
			return reg.test(str);
		},
		formParams2Json: function ($form) {
			var paramsArray = $form.serializeArray();
			var params = {};
			for (index in paramsArray) {
				params[paramsArray[index].name] = paramsArray[index].value;
			}
			return params;
		},

		formParams2Url: function ($form) {
			var paramsArray = $form.serializeArray();
			var params = "";
			for (index in paramsArray) {
				if (paramsArray[index].name && paramsArray[index].name != 'undefined') {
					params += (paramsArray[index].name + "=" + paramsArray[index].value + "&");
				};
			}
			return params.substring(0, params.length);
		},
		chromeYellowBgBugFix: function ($input) {
			$input.css("backgroundColor", "transparent");
			setTimeout(function() {
				$input.val('');
		  		try {  			   			
		  			 if (navigator.userAgent.toLowerCase().indexOf("chrome") >= 0) {   				 
		  				 $('input:-webkit-autofill').each(function(){
		  				   	$(this).after(this.outerHTML).remove();
		  				 });
		  			}
		  		} catch (e) {
		  			
		  		}   		
					
		  		$input.css("backgroundColor", "transparent");
		  	}, 50);
			setTimeout(function() {
				$input.val('');
				try {  			   			
					 if (navigator.userAgent.toLowerCase().indexOf("chrome") >= 0) {   				 
						 $('input:-webkit-autofill').each(function(){
						   	$(this).after(this.outerHTML).remove();
						 });
					}
				} catch (e) {
					
				}   		
				
				$input.css("backgroundColor", "transparent");
			}, 1000);	
		}
	};
	
})();

/**
 * ===============================================================================================
 * @utils Ajax模块
 * ===============================================================================================
 */
var AjaxUtils = (function () {
	return {
		ajaxFormSubmit: function ($form, postUrl, callback, errorCallback) {
			var paramsArray = $form.serializeArray();
			var params = {};
			for (index in paramsArray) {
				params[paramsArray[index].name] = paramsArray[index].value;
			}
			this.ajaxPost(postUrl, params, callback, errorCallback);
		},

		ajaxPost: function (url, params, callback, errorCallback) {
			$.post(url, params, function(data) {
				var json;
				try {
					json = $.parseJSON(data);					
				} catch (e) {
					if (errorCallback && typeof errorCallback == 'function') {
						errorCallback();
					} else {
						alert("系统错误");
					}
					$("#fivesaas_error_info").html(data + e);
				};
				if (json) {
					callback(json);
				}				
			});
		},
		
		ajaxHtml: function (url, params, callback) {
			$.post(url, params, function(data) {
				if (data.indexOf("ajaxErrorStatus")>0) {
					window.location.href = "/";
				} else {
					callback(data);
				}		
			});
		}
	};
})();

/**
 * ===============================================================================================
 * 数组扩展
 * ===============================================================================================
 */
Array.prototype.contains = function(elem) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i] == elem) {
			return true;
		}
	}
	return false;
};
Array.prototype.posOf = function(o) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i] == o) {
			return i;
		}
		return -1;
	}
};
Array.prototype.del = function(o) {
	var index = this.posOf(o);
	if (index != -1) {
		this.splice(index, 1);
	}
	return this;
};

/**
 * ===============================================================================================
 * date对象扩展
 * ===============================================================================================
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds() // millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

/**
 * ===============================================================================================
 * 车辆查询公共函数
 * ===============================================================================================
 */
var CarModule = (function() {
	 return {
		 	submitSearchForm:function() {
				var currentMileageBegin = $("#currentMileageBegin").val();
				var currentMileageEnd = $("#currentMileageEnd").val();
				
				var intcurrentMileageBegin = parseInt(currentMileageBegin);
				if (currentMileageBegin!='') {		
					if (isNaN(intcurrentMileageBegin) || intcurrentMileageBegin<0) {
						alert("起始里程必须是大于零的数字");
						return;
					} 
				}
				var intcurrentMileageEnd = parseInt(currentMileageEnd);
				if (currentMileageEnd!='') {		
					if (isNaN(intcurrentMileageEnd) || intcurrentMileageEnd<0) {
						alert("起始里程必须是大于零的数字");
						return;
					} 
				}

				if (intcurrentMileageBegin > intcurrentMileageEnd) {
					alert("开始里程不能大于结束里程");
					return;
				}
				
				var brandIds = "";			
				$("#brandsWell input").each(function() {
					if (this.checked) {
						if (brandIds.length>0) {
							brandIds += ",";
						}
						brandIds += $(this).val();
					}
				});
				
				var serialIds = "";		
				$("#serialsWell input").each(function() {
					if (this.checked) {
						if (serialIds.length>0) {
							serialIds += ",";
						}
						serialIds += $(this).val();
					}
				});
				
				var modelIds = "";		
				$("#modelsWell input").each(function() {
					if (this.checked) {
						if (modelIds.length>0) {
							modelIds += ",";
						}
						modelIds += $(this).val();
					}
				});

				$("#s_brandIds").val(brandIds);
				$("#s_serialIds").val(serialIds);
				$("#s_modelIds").val(modelIds);

				$('#search_form').submit();
			}
	 };
 })();
/**
 * 下载链接
 */
function appendSearchParamsBeforeDownload(downloadOnly) {	
	if (downloadOnly) {
		var paramUrl = FormUtils.formParams2Url($("#search_form"));
		var $download_link = $("#excel_download_link");
		var url = $download_link.attr("href") + "&" + paramUrl;
		$download_link.attr("href", url);		
	} else {
		var params = FormUtils.formParams2Json($("#search_form"));
		var $download_link = $("#excel_download_link");
		var url = $download_link.attr("href");
		AjaxUtils.ajaxPost(url, params, function(json) {
			$("#common_download_link").attr("href", "/stat/download_excel?excel_url=" + json.jsonData.download_link);
			$("#common_download_link").get(0).click();
		}, function() {
			alert("下载失败");
		});		
	}
}

/**
 * 显示活动贴士审核不通过原因
 */

function showFailPopover(link) {
	if($(link).attr("show")=='true') {
		$(".failPopover").popover("hide");
		$(link).popover('hide');
		$(link).attr("show","false");
	} else {
		$(".failPopover").popover("hide");
		$(link).popover('show');
		$(link).attr("show","true");
	}
}

function initFailReasonPopover() {
	
	$(".failPopover").popover({
		"title":"原因",
		"trigger":"manual",
		"placement":"left",
		"html":true  //ie7，ie6 如果后面带多余的逗号将不能正确执行
	});
}

/**
 * 活动贴士作废
 */
function discard(link, type) {
	if (type=='activity') {
		if (!confirm("确定将该活动作废吗？")) {
			return ;
		}
	} else if (type=='tip') {
		if (!confirm("确定将该贴士作废吗？")) {
			return ;
		}
	} else {
		if (!confirm("确定将该活动或者贴士作废吗？")) {
			return ;
		}
	}
	
	AjaxUtils.ajaxPost($(link).attr("href"), {}, function(json) {
		if (json.succ=='Y') {
			alert("作废成功");
			
			var $titleLink = $(link).parent().parent().find(".title_column a");
			var url = $titleLink.attr("href");
			if (url.indexOf('/activity/')>=0) {
				if (url.indexOf('/discard/')>0) {
					var id = url.substring(url.lastIndexOf("/")+1);
					url = "/activity/detail/" + id;
					$titleLink.attr("href", url);
				}
			} else {
				if (url.indexOf('/discard/')>0) {
					var id = url.substring(url.lastIndexOf("/")+1);
					url = "/tip/detail/" + id;
					$titleLink.attr("href", url);
				}
			}
			
			$(link).parent().html('<span class="badge badge-error">已作废</span>');
		} else {
			alert("作废失败：" + json.desc);
		}
	});
}
/**
 * ===============================================================================================
 * 上传模块
 * ===============================================================================================
 */
var UploadModule = (function() {	
	return {
		extArray: [".gif", ".jpg", ".png"],
		initFileuploader: function () {
			document.getElementById('file_upload_form').onsubmit = function() {
				document.getElementById('file_upload_form').target = 'upload_target';				
				var uploadFrame = document.getElementById("upload_target");
				if (uploadFrame.attachEvent) uploadFrame.attachEvent("onload",UploadModule.afterUpload);
				else uploadFrame.addEventListener("load",UploadModule.afterUpload,false);
			};
		},
		initHeadImageUploader: function () {
			document.getElementById('file_upload_form').onsubmit = function() {
				document.getElementById('file_upload_form').target = 'upload_target';				
				var uploadFrame = document.getElementById("upload_target");
				if (uploadFrame.attachEvent) uploadFrame.attachEvent("onload",UploadModule.afterUploadHeadImage);
				else uploadFrame.addEventListener("load",UploadModule.afterUploadHeadImage,false);
			};
		},
		afterUploadHeadImage: function() {
			var ret = $.trim(frames['upload_target'].document
					.getElementsByTagName("body")[0].innerHTML);
			var start = ret.indexOf('{');
			var end = ret.lastIndexOf('}');
			var data = $.parseJSON(ret.substring(start, end + 1));
			if (data.succ == "Y") { // This part happens when the image gets uploaded.			    
				document.getElementById("image_details").innerHTML = "<img id='jcropImage' src='"
						+ data.jsonData.src_url + "'/><br />";
				$("#image").val(data.jsonData.head_image_url);
				var w = data.jsonData.src_width;
				var h = data.jsonData.src_height;
				var crop_w = 0;
				var crop_h = 0;
				var x = 0;
				var y = 0;
				if (200*w>150*h) {
					crop_h = h;
					crop_w = h;
					x = (w-crop_w)/2;
				} else {
					crop_w = w;
					crop_h = w;
					y = (h-crop_h)/2;
				}
				
				jQuery('#jcropImage').Jcrop({
					trackDocument: true,
		            bgOpacity:   .4,
		            aspectRatio: 150 / 200,
		            setSelect: [x, y, crop_w, crop_h],
		            onChange: showCoords,
					onSelect: showCoords
				});
				
				function showCoords(c)
				{
					jQuery('#crop_x').val(c.x);
					jQuery('#crop_y').val(c.y);
					jQuery('#crop_w').val(c.w);
					jQuery('#crop_h').val(c.h);
					$("#crop_src_url").val(data.jsonData.src_url);
					$("#crop_image_link").show();
				};
				
				if (data.jsonData.status=="uploading") {
					document.getElementById("image_details").innerHTML = data.jsonData.progress;
				}
			} else if (data.succ == "N") { // Upload failed - show user the reason.
				alert("上传失败: " + data.desc);
			}
			$("#form").attr("submiting", "false");
			$(".submitBtn").removeClass("disabled");
			$("#file").after($("#file")[0].outerHTML).remove();
			$("#crop_image_link").hide();
		},
		afterUpload: function() {
			var ret = $.trim(frames['upload_target'].document
					.getElementsByTagName("body")[0].innerHTML);
			var start = ret.indexOf('{');
			var end = ret.lastIndexOf('}');
			var data = $.parseJSON(ret.substring(start, end + 1));
			if (data.succ == "Y") { // This part happens when the image gets uploaded.			    
				document.getElementById("image_details").innerHTML = "<img id='jcropImage' src='"
						+ data.jsonData.crop_src_url + "'/><br />";
				$("#image").val(data.jsonData.crop_src_url);
				var w = data.jsonData.show_width;
				var h = data.jsonData.show_height;
				var crop_w = 0;
				var crop_h = 0;
				var x = 0;
				var y = 0;
				if (w*114 > h*526) {
					crop_h = h;
					crop_w = h*526/114;
					x = (w-crop_w)/2;
				} else {
					crop_w = w;
					crop_h = w*114/526;
					y = (h-crop_h)/2;
				}
				
				jQuery('#jcropImage').Jcrop({
					trackDocument: true,
		            bgOpacity:   .4,
		            aspectRatio: 526 / 114,
		            setSelect: [x, y, crop_w, crop_h],
		            onChange: showCoords,
					onSelect: showCoords
				});
				
				function showCoords(c)
				{
					jQuery('#crop_x').val(c.x);
					jQuery('#crop_y').val(c.y);
					jQuery('#crop_w').val(c.w);
					jQuery('#crop_h').val(c.h);
					$("#crop_src_url").val(data.jsonData.crop_src_url);
					$("#crop_image_link").show();
				};
				
				if (data.jsonData.status=="uploading") {
					document.getElementById("image_details").innerHTML = data.jsonData.progress;
				}
			} else if (data.succ == "N") { // Upload failed - show user the reason.
				alert("上传失败: " + data.desc);
			}
			$("#form").attr("submiting", "false");
			$(".submitBtn").removeClass("disabled");
			$("#file").after($("#file")[0].outerHTML).remove();
			$("#show_cropped_images_link").hide();
			$("#crop_image_link").hide();
		},
		checkCropArea: function() {
			var x= jQuery('#crop_x').val();
			var y= jQuery('#crop_y').val();
			var w= jQuery('#crop_w').val();
			var h= jQuery('#crop_h').val();
			var valid = true;
			if (x>=0 && y>=0 && w>0 && h>0) {
				valid = true;
			} else {
				valid = false;
			}
			return valid;
		},
		doCrop: function (formId) {
			if (!UploadModule.checkCropArea()) {
				alert("请选择有效的裁剪区域");
				return;
			}
			AjaxUtils.ajaxFormSubmit(
			$("#"+formId),
			"/upload/crop",
			function (json) {
				if (json.succ=="Y") {
					alert("裁剪成功");
					var cropped_image_url = json.jsonData.cropped_image_url;
					document.getElementById("image_details").innerHTML = "<img src='"
						+ cropped_image_url + "'/><br />";
					$("#show_cropped_images_link").attr("href", $("#show_cropped_images_link").attr("href") + "?cropped_image_url=" + cropped_image_url);
					$("#show_cropped_images_link").show();
					$("#image").val(cropped_image_url);
					$("#crop_image_link").hide();
				} else {
					alert("裁剪失败");
				}				
			});
			
		},
		doHeadImageCrop: function (formId) {
			if (!UploadModule.checkCropArea()) {
				alert("请选择有效的裁剪区域");
				return;
			}
			AjaxUtils.ajaxFormSubmit(
			$("#"+formId),
			"/upload/crop?imageType=headImage",
			function (json) {
				if (json.succ=="Y") {
					alert("裁剪成功");
					var cropped_image_url = json.jsonData.cropped_image_url;
					document.getElementById("image_details").innerHTML = "<img src='"
						+ cropped_image_url + "'/><br />";
					$("#image").val(cropped_image_url);
					$("#crop_image_link").hide();
				} else {
					alert("裁剪失败");
				}				
			});
			
		},
		doUpload: function (target) {
			var allowSubmit = false;
			var file = target.value;
			if (!file) {
				alert("请先选择图片");
				return false;
			}

			while (file.indexOf("\\") != -1)
				file = file.slice(file.indexOf("\\") + 1);
			ext = file.slice(file.lastIndexOf(".")).toLowerCase();
			for ( var i = 0; i < this.extArray.length; i++) {
				if (this.extArray[i] == ext) {
					allowSubmit = true;
					break;
				}
			}
					
			if (!allowSubmit) {				
				alert("上传文件必须为下列类型:  " + (this.extArray.join("  ")) + "\n请再次选择文件上传.");
				return false;
			}
			
			var UPLOADFILEMAXSIZE=ec_contants.uploadImageMaxBytes;
			
			var _size=0;
		        if(target.files){
		            var _file=target.files[0];
		            _size=_file.fileSize ? _file.fileSize : _file.size;
		        }else{
		            //IE9以下放弃获取文件大小，改为后台判断
		        }
			if (_size>UPLOADFILEMAXSIZE) {
				alert("文件大小不能超过" + ec_contants.uploadImageMaxLimitName);
				return false;
			}
			
			$("#file_upload_form").submit();
			$("#image_details").html('<img src="/static/images/loading.gif"/>');
			$("#form").attr("submiting", "true");
			$(".submitBtn").addClass("disabled");
		}		
	};	
})();


/**
 * ===============================================================================================
 * 选择车牌模块
 * ===============================================================================================
 */
var tempContainerCarIds= [];  //为什么这两个变量放到下面代码块内部，不拥有扩展的数组方法呢？？？
var finalContainerCarIds= [];
var CarSelectModule = (function() {
	return {
		lastBrandIds: "",
		lastSerialIds: "",
		carBrandChange: function () {
			var brandIds = "";			
			$("#brandsWell input").each(function() {
				if (this.checked) {
					if (brandIds.length>0) {
						brandIds += ",";
					}
					brandIds += $(this).val();
				}
			});
			
			if (brandIds=='') {
				$('#serialsWell').empty();
				$('#modelsWell').empty();
				$('#serialsWell').parent().parent().hide();
				$("#modelsWell").parent().parent().hide();
				this.lastBrandIds = "";
				return;
			}
			
			if (brandIds != this.lastBrandIds) {
				this.lastBrandIds = brandIds;
				AjaxUtils.ajaxPost("/carinfo/serials", {
					'brandIds' : brandIds
				}, function(json) {
					if (json.succ == 'Y') {
						var carSerials = json.jsonData.carSerials;
						var $serialsWell = $('#serialsWell');
						$serialsWell.empty();
						$('#modelsWell').empty();						
						var html = '';
						for (var index=0; index<carSerials.length; index++) {
							html += '<label class="checkbox" onclick="CarSelectModule.carSerialChange();"><input type="checkbox" value="' + carSerials[index].id
									+ '" title="' + carSerials[index].name + '" />'  + carSerials[index].name  + '</label>';
						}
						$serialsWell.html(html);
						if (carSerials.length>0) {
							$serialsWell.parent().parent().show();
							$("#modelsWell").parent().parent().hide();
						} else {						
							$serialsWell.parent().parent().hide();
							$("#modelsWell").parent().parent().hide();
						}
					} else {

					}
				},function() {
					
				});
			}
		},
		
		carSerialChange: function () {
			var serialIds = "";		
			$("#serialsWell input").each(function() {
				if (this.checked) {
					if (serialIds.length>0) {
						serialIds += ",";
					}
					serialIds += $(this).val();
				}
			});
			
			if (serialIds=='') {
				$('#modelsWell').empty();
				var html = '';
				$('#modelsWell').html(html);
				this.lastSerialIds = "";
				$("#modelsWell").parent().parent().hide();
				return;
			}

			if (serialIds != this.lastSerialIds) {
				this.lastSerialIds = serialIds;
				AjaxUtils.ajaxPost("/carinfo/models", {
					'serialIds' : serialIds
				}, function(json) {
					if (json.succ == 'Y') {
						var carModels = json.jsonData.carModels;
						var $modelsWell = $('#modelsWell');
						$modelsWell.empty();
						var html = '';
						for (var index=0; index<carModels.length; index++) {
							html += '<label class="checkbox"><input type="checkbox" value="' + carModels[index].id
									+ '" title="' + carModels[index].name + '(' + carModels[index].year + ')' + '" />' + carModels[index].name + '(' + carModels[index].year + ')' + '</label>';
							
						}
						$modelsWell.html(html);
						if (carModels.length>0) {							
							$modelsWell.parent().parent().show();
						} else {
							$modelsWell.parent().parent().hide();
						}
					} else {
						
					}
				});
			}
		},
		
		selectCarNo: function () {
			var params = {};
			
			var brandIds = "";			
			$("#brandsWell input").each(function() {
				if (this.checked) {
					if (brandIds.length>0) {
						brandIds += ",";
					}
					brandIds += $(this).val();
				}
			});
			
			var serialIds = "";		
			$("#serialsWell input").each(function() {
				if (this.checked) {
					if (serialIds.length>0) {
						serialIds += ",";
					}
					serialIds += $(this).val();
				}
			});
			
			var modelIds = "";		
			$("#modelsWell input").each(function() {
				if (this.checked) {
					if (modelIds.length>0) {
						modelIds += ",";
					}
					modelIds += $(this).val();
				}
			});
			
			var ctimeBegin = $("#ctimeBegin").val();
			var ctimeEnd = $("#ctimeEnd").val();

			var currentMileageBegin = $("#currentMileageBegin").val();
			var currentMileageEnd = $("#currentMileageEnd").val();

			var ctimeEnd = $("#ctimeEnd").val();

			if (brandIds && brandIds.length > 0 && brandIds!='请选择厂牌') {
				params.brandIds = brandIds;
			}
			if (serialIds && serialIds.length > 0 && serialIds!='请选择车系') {
				params.serialIds = serialIds;
			}
			if (modelIds && modelIds.length > 0 && modelIds!='请选择车型') {
				params.modelIds = modelIds;
			}

			if (ctimeBegin != '' && ctimeEnd != '') {
				if (ctimeBegin > ctimeEnd) {
					alert("注册开始时间不能大于结束时间");
					return;
				}
			}

			params.ctimeBegin = ctimeBegin;
			params.ctimeEnd = ctimeEnd;

			var intcurrentMileageBegin = parseInt(currentMileageBegin);
			if (currentMileageBegin != '') {
				if (isNaN(intcurrentMileageBegin) || intcurrentMileageBegin < 0) {
					alert("起始里程必须是大于零的数字");
					return;
				}
			}
			var intcurrentMileageEnd = parseInt(currentMileageEnd);
			if (currentMileageEnd != '') {
				if (isNaN(intcurrentMileageEnd) || intcurrentMileageEnd < 0) {
					alert("起始里程必须是大于零的数字");
					return;
				}
			}

			if (intcurrentMileageBegin > intcurrentMileageEnd) {
				alert("开始里程不能大于结束里程");
				return;
			}

			params.currentMileageBegin = currentMileageBegin;
			params.currentMileageEnd = currentMileageEnd;

			params.addType = $("#addType").val();

			$tempContainer = $("#tempContainer");
			AjaxUtils.ajaxPost(
					'/carinfo/carinfos',
					params,
					function(json) {
						if (json.succ == 'Y') {
							var carInfos = json.jsonData.carInfos;
							$.each(
											carInfos,
											function(i, value) { // 利用jquery遍历可以过滤非索引属性
												if (!tempContainerCarIds
														.contains(carInfos[i].carId)) {
													var carInfo = '<span id="car_'
															+ carInfos[i].carId
															+ '" style="float:left;width:100px;"><input type="checkbox" checked="checked" carNo="'
															+ carInfos[i].carNo
															+ '"  value="'
															+ carInfos[i].carId + '" '
															+ '>' + carInfos[i].carNo
															+ '</input></span>';
													$tempContainer.append(carInfo);
													tempContainerCarIds
															.push(carInfos[i].carId);
												}
											});
						} else {
							alert('没有符合条件的车辆信息!');
						}
					});
		},
		
		move2FinalContainer: function () {
			$final = $("#finalContainer");
			$temp = $("#tempContainer");

			$temp
					.find("input")
					.each(
							function() {
								if (this.checked) {
									var carId = $(this).val();
									var carNo = $(this).attr("carNo");
									$(this).parent().remove();

									tempContainerCarIds.del(carId);
									if (!finalContainerCarIds.contains(carId)) {
										var carInfo = '<span id="car_'
												+ carId
												+ '" style="float:left;width:100px;"><input type="checkbox" checked="checked" carNo="'
												+ carNo + '" value="' + carId + '" '
												+ '>' + carNo + '</input></span>';
										$final.append(carInfo);
										finalContainerCarIds.push(carId);
									}
								}
							});
			
			var length = $final.find("input").length;
			if (!isNaN(length)) {
				$("#selectedCarCount").html("(" + length + "辆)");
			} else {
				$("#selectedCarCount").html("");
			}
		},

		move2TempContainer: function () {
			$final = $("#finalContainer");
			$temp = $("#tempContainer");

			$final.find("input").each(
							function() {
								if (this.checked) {
									var carId = $(this).val();
									var carNo = $(this).attr("carNo");
									$(this).parent().remove();

									finalContainerCarIds.del(carId);
									if (!tempContainerCarIds.contains(carId)) {
										var carInfo = '<span id="car_'
												+ carId
												+ '" style="float:left;width:100px;"><input type="checkbox" carNo="'
												+ carNo + '" value="' + carId + '" '
												+ '>' + carNo + '</input></span>';
										$temp.append(carInfo);
										tempContainerCarIds.push(carId);
									}
								}
							});
			var length = $final.find("input").length;
			if (!isNaN(length)) {
				$("#selectedCarCount").html("(" + length + "辆)");
			} else {
				$("#selectedCarCount").html("");
			}
		},
		selectAll: function (isTemp) {
			var container;
			if (isTemp) {
				container = $("#tempContainer");
			} else {
				container = $("#finalContainer");
			}
			container.find("input").each(function() {
				this.checked = true;
			});
		},
		reverseSelect: function (isTemp) {
			var container;
			if (isTemp) {
				container = $("#tempContainer");
			} else {
				container = $("#finalContainer");
			}
			container.find("input").each(function() {
				this.checked = !this.checked;
			});
		}, 
		clearContainer: function (isTemp) {
			this.selectAll(isTemp);
			if (isTemp) {
				$temp = $("#tempContainer");
				$temp.empty();
				tempContainerCarIds = [];
			} else {
				this.move2TempContainer();
				finalContainerCarIds = [];
			}
		},

		multiSelectBoxInit: function () {
			$(".multiSelectBox").each(function() {
				$selectBox = $(this);
				$selectBox.find(".input-append .btn").click(function() {
					$well = $(this).parent().parent().find(".well");
					if ($well.is(":visible")) {
						$well.hide();
					} else {
						$(".multiSelectBox .well").hide();
						$well.show();
					}
					
					CarSelectModule.setSelectValues($(this).parent().parent());
				});
			});
		},
		
		closeBrandsWell: function ($well) {
			$well.hide();
			this.setSelectValues($well.parent());
			this.carBrandChange();
		},
		
		closeSerialsWell: function ($well) {
			$well.hide();
			this.setSelectValues($well.parent());
			this.carSerialChange();
		},
		
		closeModelsWell: function closeModelsWell($well) {
			$well.hide();
			this.setSelectValues($well.parent());
		},
		
		setSelectValues: function ($selectBox) {
			$input = $selectBox.find(".input-append input");
			var values = "";
			$selectBox.find(".checkbox input").each(function() {
				if (this.checked) {
					if (values.length>0) {
						values += ",";
					}
					values += $(this).val();
				}
			});
			$input.attr("values", values);
		}
	};
})();



!function ($) {

  "use strict"; // jshint ;_;


 /* TOOLTIP PUBLIC CLASS DEFINITION
  * =============================== */

  var Tooltip = function (element, options) {
    this.init('tooltip', element, options)
  }

  Tooltip.prototype = {

    constructor: Tooltip

  , init: function (type, element, options) {
      var eventIn
        , eventOut

      this.type = type
      this.$element = $(element)
      this.options = this.getOptions(options)
      this.enabled = true

      if (this.options.trigger == 'click') {
        this.$element.on('click.' + this.type, this.options.selector, $.proxy(this.toggle, this))
      } else if (this.options.trigger != 'manual') {
        eventIn = this.options.trigger == 'hover' ? 'mouseenter' : 'focus'
        eventOut = this.options.trigger == 'hover' ? 'mouseleave' : 'blur'
        this.$element.on(eventIn + '.' + this.type, this.options.selector, $.proxy(this.enter, this))
        this.$element.on(eventOut + '.' + this.type, this.options.selector, $.proxy(this.leave, this))
      }

      this.options.selector ?
        (this._options = $.extend({}, this.options, { trigger: 'manual', selector: '' })) :
        this.fixTitle()
    }

  , getOptions: function (options) {
      options = $.extend({}, $.fn[this.type].defaults, options, this.$element.data())

      if (options.delay && typeof options.delay == 'number') {
        options.delay = {
          show: options.delay
        , hide: options.delay
        }
      }

      return options
    }

  , enter: function (e) {
      var self = $(e.currentTarget)[this.type](this._options).data(this.type)

      if (!self.options.delay || !self.options.delay.show) return self.show()

      clearTimeout(this.timeout)
      self.hoverState = 'in'
      this.timeout = setTimeout(function() {
        if (self.hoverState == 'in') self.show()
      }, self.options.delay.show)
    }

  , leave: function (e) {
      var self = $(e.currentTarget)[this.type](this._options).data(this.type)

      if (this.timeout) clearTimeout(this.timeout)
      if (!self.options.delay || !self.options.delay.hide) return self.hide()

      self.hoverState = 'out'
      this.timeout = setTimeout(function() {
        if (self.hoverState == 'out') self.hide()
      }, self.options.delay.hide)
    }

  , show: function () {
      var $tip
        , inside
        , pos
        , actualWidth
        , actualHeight
        , placement
        , tp

      if (this.hasContent() && this.enabled) {
        $tip = this.tip()
        this.setContent()

        if (this.options.animation) {
          $tip.addClass('fade')
        }

        placement = typeof this.options.placement == 'function' ?
          this.options.placement.call(this, $tip[0], this.$element[0]) :
          this.options.placement

        inside = /in/.test(placement)

        $tip
          .detach()
          .css({ top: 0, left: 0, display: 'block' })
          .insertAfter(this.$element)

        pos = this.getPosition(inside)

        actualWidth = $tip[0].offsetWidth
        actualHeight = $tip[0].offsetHeight

        switch (inside ? placement.split(' ')[1] : placement) {
          case 'bottom':
            tp = {top: pos.top + pos.height, left: pos.left + pos.width / 2 - actualWidth / 2}
            break
          case 'top':
            tp = {top: pos.top - actualHeight, left: pos.left + pos.width / 2 - actualWidth / 2}
            break
          case 'left':
            tp = {top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left - actualWidth}
            break
          case 'right':
            tp = {top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left + pos.width}
            break
        }

        $tip
          .offset(tp)
          .addClass(placement)
          .addClass('in')
      }
    }

  , setContent: function () {
      var $tip = this.tip()
        , title = this.getTitle()

      $tip.find('.tooltip-inner')[this.options.html ? 'html' : 'text'](title)
      $tip.removeClass('fade in top bottom left right')
    }

  , hide: function () {
      var that = this
        , $tip = this.tip()

      $tip.removeClass('in')

      function removeWithAnimation() {
        var timeout = setTimeout(function () {
          $tip.off($.support.transition.end).detach()
        }, 500)

        $tip.one($.support.transition.end, function () {
          clearTimeout(timeout)
          $tip.detach()
        })
      }

      $.support.transition && this.$tip.hasClass('fade') ?
        removeWithAnimation() :
        $tip.detach()

      return this
    }

  , fixTitle: function () {
      var $e = this.$element
      if ($e.attr('title') || typeof($e.attr('data-original-title')) != 'string') {
        $e.attr('data-original-title', $e.attr('title') || '').removeAttr('title')
      }
    }

  , hasContent: function () {
      return this.getTitle()
    }

  , getPosition: function (inside) {
      return $.extend({}, (inside ? {top: 0, left: 0} : this.$element.offset()), {
        width: this.$element[0].offsetWidth
      , height: this.$element[0].offsetHeight
      })
    }

  , getTitle: function () {
      var title
        , $e = this.$element
        , o = this.options

      title = $e.attr('data-original-title')
        || (typeof o.title == 'function' ? o.title.call($e[0]) :  o.title)

      return title
    }

  , tip: function () {
      return this.$tip = this.$tip || $(this.options.template)
    }

  , validate: function () {
      if (!this.$element[0].parentNode) {
        this.hide()
        this.$element = null
        this.options = null
      }
    }

  , enable: function () {
      this.enabled = true
    }

  , disable: function () {
      this.enabled = false
    }

  , toggleEnabled: function () {
      this.enabled = !this.enabled
    }

  , toggle: function (e) {
      var self = $(e.currentTarget)[this.type](this._options).data(this.type)
      self[self.tip().hasClass('in') ? 'hide' : 'show']()
    }

  , destroy: function () {
      this.hide().$element.off('.' + this.type).removeData(this.type)
    }

  }


 /* TOOLTIP PLUGIN DEFINITION
  * ========================= */

  $.fn.tooltip = function ( option ) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('tooltip')
        , options = typeof option == 'object' && option
      if (!data) $this.data('tooltip', (data = new Tooltip(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.tooltip.Constructor = Tooltip

  $.fn.tooltip.defaults = {
    animation: true
  , placement: 'top'
  , selector: false
  , template: '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
  , trigger: 'hover'
  , title: ''
  , delay: 0
  , html: false
  }

}(window.jQuery);
/**
 * popover
 */

!function ($) {

	  "use strict"; // jshint ;_;


	 /* POPOVER PUBLIC CLASS DEFINITION
	  * =============================== */

	  var Popover = function (element, options) {
	    this.init('popover', element, options);
	  };


	  /* NOTE: POPOVER EXTENDS BOOTSTRAP-TOOLTIP.js
	     ========================================== */

	  Popover.prototype = $.extend({}, $.fn.tooltip.Constructor.prototype, {

	    constructor: Popover

	  , setContent: function () {
	      var $tip = this.tip()
	        , title = this.getTitle()
	        , content = this.getContent();

	      $tip.find('.popover-title')[this.options.html ? 'html' : 'text'](title);
	      $tip.find('.popover-content > *')[this.options.html ? 'html' : 'text'](content);

	      $tip.removeClass('fade top bottom left right in');
	    }

	  , hasContent: function () {
	      return this.getTitle() || this.getContent();
	    }

	  , getContent: function () {
	      var content
	        , $e = this.$element
	        , o = this.options;

	      content = $e.attr('data-content')
	        || (typeof o.content == 'function' ? o.content.call($e[0]) :  o.content);

	      return content;
	    }

	  , tip: function () {
	      if (!this.$tip) {
	        this.$tip = $(this.options.template);
	      }
	      return this.$tip;
	    }

	  , destroy: function () {
	      this.hide().$element.off('.' + this.type).removeData(this.type);
	    }

	  });


	 /* POPOVER PLUGIN DEFINITION
	  * ======================= */

	  $.fn.popover = function (option) {
	    return this.each(function () {
	      var $this = $(this)
	        , data = $this.data('popover')
	        , options = typeof option == 'object' && option;
	      if (!data) $this.data('popover', (data = new Popover(this, options)));
	      if (typeof option == 'string') data[option]();
	    });
	  };

	  $.fn.popover.Constructor = Popover;

	  $.fn.popover.defaults = $.extend({} , $.fn.tooltip.defaults, {
	    placement: 'right'
	  , trigger: 'click'
	  , content: ''
	  , template: '<div class="popover"><div class="arrow"></div><div class="popover-inner"><h3 class="popover-title"></h3><div class="popover-content"><p></p></div></div></div>'
	  });

	}(window.jQuery);
