/*
 *  jQuery selectBox - A cosmetic, styleable replacement for SELECT elements
 *
 *  Copyright 2012 Cory LaViska for A Beautiful Site, LLC.
 *
 *  https://github.com/claviska/jquery-selectBox
 *
 *  Licensed under both the MIT license and the GNU GPLv2 (same as jQuery: http://jquery.org/license)
 *
 */
if (jQuery)(function($) {
	$.extend($.fn, {
		selectBox: function(method, data) {
			var typeTimer, typeSearch = '',
				isMac = navigator.platform.match(/mac/i);
			//
			// Private methods
			//
			var init = function(select, data) {
					var options;
					// Disable for iOS devices (their native controls are more suitable for a touch device)
					if (navigator.userAgent.match(/iPad|iPhone|Android|IEMobile|BlackBerry/i)) return false;
					// Element must be a select control
					if (select.tagName.toLowerCase() !== 'select') return false;
					select = $(select);
					if (select.data('selectBox-control')) return false;
					var control = $('<a class="selectBox" />'),
						inline = select.attr('multiple') || parseInt(select.attr('size')) > 1;
					var settings = data || {};
					control.addClass(select.attr('class')).attr('title', select.attr('title') || '').attr('tabindex', parseInt(select.attr('tabindex'))).css('display', 'inline-block').bind('focus.selectBox', function() {
						if (this !== document.activeElement && document.body !== document.activeElement) $(document.activeElement).blur();
						if (control.hasClass('selectBox-active')) return;
						control.addClass('selectBox-active');
						select.trigger('focus');
					}).bind('blur.selectBox', function() {
						if (!control.hasClass('selectBox-active')) return;
						control.removeClass('selectBox-active');
						select.trigger('blur');
					});
					if (!$(window).data('selectBox-bindings')) {
						$(window).data('selectBox-bindings', true).bind('scroll.selectBox', hideMenus).bind('resize.selectBox', hideMenus);
					}
					if (select.attr('disabled')) control.addClass('selectBox-disabled');
					// Focus on control when label is clicked
					select.bind('click.selectBox', function(event) {
						control.focus();
						event.preventDefault();
					});
					// Generate control
					if (inline) {
						//
						// Inline controls
						//
						options = getOptions(select, 'inline');
						control.append(options).data('selectBox-options', options).addClass('selectBox-inline selectBox-menuShowing').bind('keydown.selectBox', function(event) {
							handleKeyDown(select, event);
						}).bind('keypress.selectBox', function(event) {
							handleKeyPress(select, event);
						}).bind('mousedown.selectBox', function(event) {
							if ($(event.target).is('A.selectBox-inline')) event.preventDefault();
							if (!control.hasClass('selectBox-focus')) control.focus();
						}).insertBefore(select);
						// Auto-height based on size attribute
						if (!select[0].style.height) {
							var size = select.attr('size') ? parseInt(select.attr('size')) : 5;
							// Draw a dummy control off-screen, measure, and remove it
							var tmp = control.clone().removeAttr('id').css({
								position: 'absolute',
								top: '-9999em'
							}).show().appendTo('body');
							tmp.find('.selectBox-options').html('<li><a>\u00A0</a></li>');
							var optionHeight = parseInt(tmp.find('.selectBox-options A:first').html('&nbsp;').outerHeight());
							tmp.remove();
							control.height(optionHeight * size);
						}
						disableSelection(control);
					} else {
						//
						// Dropdown controls
						//
						var label = $('<span class="selectBox-label" />'),
							arrow = $('<span class="selectBox-arrow" />');
						// Update label
						label.attr('class', getLabelClass(select)).text(getLabelText(select));
						options = getOptions(select, 'dropdown');
						options.appendTo('BODY');
						control.data('selectBox-options', options).addClass('selectBox-dropdown').append(label).append(arrow).bind('mousedown.selectBox', function(event) {
							if (control.hasClass('selectBox-menuShowing')) {
								hideMenus();
							} else {
								event.stopPropagation();
								// Webkit fix to prevent premature selection of options
								options.data('selectBox-down-at-x', event.screenX).data('selectBox-down-at-y', event.screenY);
								showMenu(select);
							}
						}).bind('keydown.selectBox', function(event) {
							handleKeyDown(select, event);
						}).bind('keypress.selectBox', function(event) {
							handleKeyPress(select, event);
						}).bind('open.selectBox', function(event, triggerData) {
							if (triggerData && triggerData._selectBox === true) return;
							showMenu(select);
						}).bind('close.selectBox', function(event, triggerData) {
							if (triggerData && triggerData._selectBox === true) return;
							hideMenus();
						}).insertBefore(select);
						// Set label width
						var labelWidth = control.width() - 27;
						label.width(labelWidth);
						disableSelection(control);
					}
					// Store data for later use and show the control
					select.addClass('selectBox selectBoxHide').data('selectBox-control', control).data('selectBox-settings', settings);
				};
			var getOptions = function(select, type) {
					var options;
					// Private function to handle recursion in the getOptions function.
					var _getOptions = function(select, options) {
							// Loop through the set in order of element children.
							select.children('OPTION, OPTGROUP').each(function() {
								// If the element is an option, add it to the list.
								if ($(this).is('OPTION')) {
									// Check for a value in the option found.
									if ($(this).length > 0) {
										// Create an option form the found element.
										generateOptions($(this), options);
									} else {
										// No option information found, so add an empty.
										options.append('<li>\u00A0</li>');
									}
								} else {
									// If the element is an option group, add the group and call this function on it.
									var optgroup = $('<li class="selectBox-optgroup" />');
									optgroup.text($(this).attr('label'));
									options.append(optgroup);
									options = _getOptions($(this), options);
								}
							});
							// Return the built strin
							return options;
						};
					switch (type) {
					case 'inline':
						options = $('<ul class="selectBox-options" />');
						options = _getOptions(select, options);
						options.find('A').bind('mouseover.selectBox', function(event) {
							addHover(select, $(this).parent());
						}).bind('mouseout.selectBox', function(event) {
							removeHover(select, $(this).parent());
						}).bind('mousedown.selectBox', function(event) {
							event.preventDefault(); // Prevent options from being "dragged"
							if (!select.selectBox('control').hasClass('selectBox-active')) select.selectBox('control').focus();
						}).bind('mouseup.selectBox', function(event) {
							hideMenus();
							selectOption(select, $(this).parent(), event);
						});
						disableSelection(options);
						return options;
					case 'dropdown':
						options = $('<ul class="selectBox-dropdown-menu selectBox-options" />');
						options = _getOptions(select, options);
						options.data('selectBox-select', select).css('display', 'none').appendTo('BODY').find('A').bind('mousedown.selectBox', function(event) {
							event.preventDefault(); // Prevent options from being "dragged"
							if (event.screenX === options.data('selectBox-down-at-x') && event.screenY === options.data('selectBox-down-at-y')) {
								options.removeData('selectBox-down-at-x').removeData('selectBox-down-at-y');
								hideMenus();
							}
						}).bind('mouseup.selectBox', function(event) {
							if (event.screenX === options.data('selectBox-down-at-x') && event.screenY === options.data('selectBox-down-at-y')) {
								return;
							} else {
								options.removeData('selectBox-down-at-x').removeData('selectBox-down-at-y');
							}
							selectOption(select, $(this).parent());
							hideMenus();
						}).bind('mouseover.selectBox', function(event) {
							addHover(select, $(this).parent());
						}).bind('mouseout.selectBox', function(event) {
							removeHover(select, $(this).parent());
						});
						// Inherit classes for dropdown menu
						var classes = select.attr('class') || '';
						if (classes !== '') {
							classes = classes.split(' ');
							for (var i in classes) options.addClass(classes[i] + '-selectBox-dropdown-menu');
						}
						disableSelection(options);
						return options;
					}
				};
			var getLabelClass = function(select) {
					var selected = $(select).find('OPTION:selected');
					return ('selectBox-label ' + (selected.attr('class') || '')).replace(/\s+$/, '');
				};
			var getLabelText = function(select) {
					var selected = $(select).find('OPTION:selected');
					return selected.text() || '\u00A0';
				};
			var setLabel = function(select) {
					select = $(select);
					var control = select.data('selectBox-control');
					if (!control) return;
					control.find('.selectBox-label').attr('class', getLabelClass(select)).text(getLabelText(select));
				};
			var destroy = function(select) {
					select = $(select);
					var control = select.data('selectBox-control');
					if (!control) return;
					var options = control.data('selectBox-options');
					options.remove();
					control.remove();
					select.removeClass('selectBox').removeData('selectBox-control').data('selectBox-control', null).removeData('selectBox-settings').data('selectBox-settings', null).show();
				};
			var refresh = function(select) {
					select = $(select);
					select.selectBox('options', select.html());
				};
			var showMenu = function(select) {
					select = $(select);
					var control = select.data('selectBox-control'),
						settings = select.data('selectBox-settings'),
						options = control.data('selectBox-options');
					if (control.hasClass('selectBox-disabled')) return false;
					hideMenus();
					var borderBottomWidth = isNaN(control.css('borderBottomWidth')) ? 0 : parseInt(control.css('borderBottomWidth'));
					// Menu position
					options.width(control.innerWidth()).css({
						top: control.offset().top + control.outerHeight() - borderBottomWidth,
						left: control.offset().left
					});
					if (select.triggerHandler('beforeopen')) return false;
					var dispatchOpenEvent = function() {
							select.triggerHandler('open', {
								_selectBox: true
							});
						};
					// Show menu
					switch (settings.menuTransition) {
					case 'fade':
						options.fadeIn(settings.menuSpeed, dispatchOpenEvent);
						break;
					case 'slide':
						options.slideDown(settings.menuSpeed, dispatchOpenEvent);
						break;
					default:
						options.show(settings.menuSpeed, dispatchOpenEvent);
						break;
					}
					if (!settings.menuSpeed) dispatchOpenEvent();
					// Center on selected option
					var li = options.find('.selectBox-selected:first');
					keepOptionInView(select, li, true);
					addHover(select, li);
					control.addClass('selectBox-menuShowing');
					$(document).bind('mousedown.selectBox', function(event) {
						if ($(event.target).parents().andSelf().hasClass('selectBox-options')) return;
						hideMenus();
					});
				};
			var hideMenus = function() {
					if ($(".selectBox-dropdown-menu:visible").length === 0) return;
					$(document).unbind('mousedown.selectBox');
					$(".selectBox-dropdown-menu").each(function() {
						var options = $(this),
							select = options.data('selectBox-select'),
							control = select.data('selectBox-control'),
							settings = select.data('selectBox-settings');
						if (select.triggerHandler('beforeclose')) return false;
						var dispatchCloseEvent = function() {
								select.triggerHandler('close', {
									_selectBox: true
								});
							};
						if (settings) {
							switch (settings.menuTransition) {
							case 'fade':
								options.fadeOut(settings.menuSpeed, dispatchCloseEvent);
								break;
							case 'slide':
								options.slideUp(settings.menuSpeed, dispatchCloseEvent);
								break;
							default:
								options.hide(settings.menuSpeed, dispatchCloseEvent);
								break;
							}
							if (!settings.menuSpeed) dispatchCloseEvent();
							control.removeClass('selectBox-menuShowing');
						} else {
							$(this).hide();
							$(this).triggerHandler('close', {
								_selectBox: true
							});
							$(this).removeClass('selectBox-menuShowing');
						}
					});
				};
			var selectOption = function(select, li, event) {
					select = $(select);
					li = $(li);
					var control = select.data('selectBox-control'),
						settings = select.data('selectBox-settings');
					if (control.hasClass('selectBox-disabled')) return false;
					if (li.length === 0 || li.hasClass('selectBox-disabled')) return false;
					if (select.attr('multiple')) {
						// If event.shiftKey is true, this will select all options between li and the last li selected
						if (event.shiftKey && control.data('selectBox-last-selected')) {
							li.toggleClass('selectBox-selected');
							var affectedOptions;
							if (li.index() > control.data('selectBox-last-selected').index()) {
								affectedOptions = li.siblings().slice(control.data('selectBox-last-selected').index(), li.index());
							} else {
								affectedOptions = li.siblings().slice(li.index(), control.data('selectBox-last-selected').index());
							}
							affectedOptions = affectedOptions.not('.selectBox-optgroup, .selectBox-disabled');
							if (li.hasClass('selectBox-selected')) {
								affectedOptions.addClass('selectBox-selected');
							} else {
								affectedOptions.removeClass('selectBox-selected');
							}
						} else if ((isMac && event.metaKey) || (!isMac && event.ctrlKey)) {
							li.toggleClass('selectBox-selected');
						} else {
							li.siblings().removeClass('selectBox-selected');
							li.addClass('selectBox-selected');
						}
					} else {
						li.siblings().removeClass('selectBox-selected');
						li.addClass('selectBox-selected');
					}
					if (control.hasClass('selectBox-dropdown')) {
						control.find('.selectBox-label').text(li.text());
					}
					// Update original control's value
					var i = 0,
						selection = [];
					if (select.attr('multiple')) {
						control.find('.selectBox-selected A').each(function() {
							selection[i++] = $(this).attr('rel');
						});
					} else {
						selection = li.find('A').attr('rel');
					}
					// Remember most recently selected item
					control.data('selectBox-last-selected', li);
					// Change callback
					if (select.val() !== selection) {
						select.val(selection);
						setLabel(select);
						select.trigger('change');
					}
					return true;
				};
			var addHover = function(select, li) {
					select = $(select);
					li = $(li);
					var control = select.data('selectBox-control'),
						options = control.data('selectBox-options');
					options.find('.selectBox-hover').removeClass('selectBox-hover');
					li.addClass('selectBox-hover');
				};
			var removeHover = function(select, li) {
					select = $(select);
					li = $(li);
					var control = select.data('selectBox-control'),
						options = control.data('selectBox-options');
					options.find('.selectBox-hover').removeClass('selectBox-hover');
				};
			var keepOptionInView = function(select, li, center) {
					if (!li || li.length === 0) return;
					select = $(select);
					var control = select.data('selectBox-control'),
						options = control.data('selectBox-options'),
						scrollBox = control.hasClass('selectBox-dropdown') ? options : options.parent(),
						top = parseInt(li.offset().top - scrollBox.position().top),
						bottom = parseInt(top + li.outerHeight());
					if (center) {
						scrollBox.scrollTop(li.offset().top - scrollBox.offset().top + scrollBox.scrollTop() - (scrollBox.height() / 2));
					} else {
						if (top < 0) {
							scrollBox.scrollTop(li.offset().top - scrollBox.offset().top + scrollBox.scrollTop());
						}
						if (bottom > scrollBox.height()) {
							scrollBox.scrollTop((li.offset().top + li.outerHeight()) - scrollBox.offset().top + scrollBox.scrollTop() - scrollBox.height());
						}
					}
				};
			var handleKeyDown = function(select, event) {
					//
					// Handles open/close and arrow key functionality
					//
					select = $(select);
					var control = select.data('selectBox-control'),
						options = control.data('selectBox-options'),
						settings = select.data('selectBox-settings'),
						totalOptions = 0,
						i = 0;
					if (control.hasClass('selectBox-disabled')) return;
					switch (event.keyCode) {
					case 8:
						// backspace
						event.preventDefault();
						typeSearch = '';
						break;
					case 9:
						// tab
					case 27:
						// esc
						hideMenus();
						removeHover(select);
						break;
					case 13:
						// enter
						if (control.hasClass('selectBox-menuShowing')) {
							selectOption(select, options.find('LI.selectBox-hover:first'), event);
							if (control.hasClass('selectBox-dropdown')) hideMenus();
						} else {
							showMenu(select);
						}
						break;
					case 38:
						// up
					case 37:
						// left
						event.preventDefault();
						if (control.hasClass('selectBox-menuShowing')) {
							var prev = options.find('.selectBox-hover').prev('LI');
							totalOptions = options.find('LI:not(.selectBox-optgroup)').length;
							i = 0;
							while (prev.length === 0 || prev.hasClass('selectBox-disabled') || prev.hasClass('selectBox-optgroup')) {
								prev = prev.prev('LI');
								if (prev.length === 0) {
									if (settings.loopOptions) {
										prev = options.find('LI:last');
									} else {
										prev = options.find('LI:first');
									}
								}
								if (++i >= totalOptions) break;
							}
							addHover(select, prev);
							selectOption(select, prev, event);
							keepOptionInView(select, prev);
						} else {
							showMenu(select);
						}
						break;
					case 40:
						// down
					case 39:
						// right
						event.preventDefault();
						if (control.hasClass('selectBox-menuShowing')) {
							var next = options.find('.selectBox-hover').next('LI');
							totalOptions = options.find('LI:not(.selectBox-optgroup)').length;
							i = 0;
							while (next.length === 0 || next.hasClass('selectBox-disabled') || next.hasClass('selectBox-optgroup')) {
								next = next.next('LI');
								if (next.length === 0) {
									if (settings.loopOptions) {
										next = options.find('LI:first');
									} else {
										next = options.find('LI:last');
									}
								}
								if (++i >= totalOptions) break;
							}
							addHover(select, next);
							selectOption(select, next, event);
							keepOptionInView(select, next);
						} else {
							showMenu(select);
						}
						break;
					}
				};
			var handleKeyPress = function(select, event) {
					//
					// Handles type-to-find functionality
					//
					select = $(select);
					var control = select.data('selectBox-control'),
						options = control.data('selectBox-options');
					if (control.hasClass('selectBox-disabled')) return;
					switch (event.keyCode) {
					case 9:
						// tab
					case 27:
						// esc
					case 13:
						// enter
					case 38:
						// up
					case 37:
						// left
					case 40:
						// down
					case 39:
						// right
						// Don't interfere with the keydown event!
						break;
					default:
						// Type to find
						if (!control.hasClass('selectBox-menuShowing')) showMenu(select);
						event.preventDefault();
						clearTimeout(typeTimer);
						typeSearch += String.fromCharCode(event.charCode || event.keyCode);
						options.find('A').each(function() {
							if ($(this).text().substr(0, typeSearch.length).toLowerCase() === typeSearch.toLowerCase()) {
								addHover(select, $(this).parent());
								keepOptionInView(select, $(this).parent());
								return false;
							}
						});
						// Clear after a brief pause
						typeTimer = setTimeout(function() {
							typeSearch = '';
						}, 1000);
						break;
					}
				};
			var enable = function(select) {
					select = $(select);
					select.attr('disabled', false);
					var control = select.data('selectBox-control');
					if (!control) return;
					control.removeClass('selectBox-disabled');
				};
			var disable = function(select) {
					select = $(select);
					select.attr('disabled', true);
					var control = select.data('selectBox-control');
					if (!control) return;
					control.addClass('selectBox-disabled');
				};
			var setValue = function(select, value) {
					select = $(select);
					select.val(value);
					value = select.val(); // IE9's select would be null if it was set with a non-exist options value
					if (value === null) { // So check it here and set it with the first option's value if possible
						value = select.children().first().val();
						select.val(value);
					}
					var control = select.data('selectBox-control');
					if (!control) return;
					var settings = select.data('selectBox-settings'),
						options = control.data('selectBox-options');
					// Update label
					setLabel(select);
					// Update control values
					options.find('.selectBox-selected').removeClass('selectBox-selected');
					options.find('A').each(function() {
						if (typeof(value) === 'object') {
							for (var i = 0; i < value.length; i++) {
								if ($(this).attr('rel') == value[i]) {
									$(this).parent().addClass('selectBox-selected');
								}
							}
						} else {
							if ($(this).attr('rel') == value) {
								$(this).parent().addClass('selectBox-selected');
							}
						}
					});
					if (settings.change) settings.change.call(select);
				};
			var setOptions = function(select, options) {
					select = $(select);
					var control = select.data('selectBox-control'),
						settings = select.data('selectBox-settings');
					switch (typeof(data)) {
					case 'string':
						select.html(data);
						break;
					case 'object':
						select.html('');
						for (var i in data) {
							if (data[i] === null) continue;
							if (typeof(data[i]) === 'object') {
								var optgroup = $('<optgroup label="' + i + '" />');
								for (var j in data[i]) {
									optgroup.append('<option value="' + j + '">' + data[i][j] + '</option>');
								}
								select.append(optgroup);
							} else {
								var option = $('<option value="' + i + '">' + data[i] + '</option>');
								select.append(option);
							}
						}
						break;
					}
					if (!control) return;
					// Remove old options
					control.data('selectBox-options').remove();
					// Generate new options
					var type = control.hasClass('selectBox-dropdown') ? 'dropdown' : 'inline';
					options = getOptions(select, type);
					control.data('selectBox-options', options);
					switch (type) {
					case 'inline':
						control.append(options);
						break;
					case 'dropdown':
						// Update label
						setLabel(select);
						$("BODY").append(options);
						break;
					}
				};
			var disableSelection = function(selector) {
					$(selector).css('MozUserSelect', 'none').bind('selectstart', function(event) {
						event.preventDefault();
					});
				};
			var generateOptions = function(self, options) {
					var li = $('<li />'),
						a = $('<a />');
					li.addClass(self.attr('class'));
					li.data(self.data());
					a.attr('rel', self.val()).text(self.text());
					li.append(a);
					if (self.attr('disabled')) li.addClass('selectBox-disabled');
					if (self.attr('selected')) li.addClass('selectBox-selected');
					options.append(li);
				};
			//
			// Public methods
			//
			switch (method) {
			case 'control':
				return $(this).data('selectBox-control');
			case 'settings':
				if (!data) return $(this).data('selectBox-settings');
				$(this).each(function() {
					$(this).data('selectBox-settings', $.extend(true, $(this).data('selectBox-settings'), data));
				});
				break;
			case 'options':
				// Getter
				if (data === undefined) return $(this).data('selectBox-control').data('selectBox-options');
				// Setter
				$(this).each(function() {
					setOptions(this, data);
				});
				break;
			case 'value':
				// Empty string is a valid value
				if (data === undefined) return $(this).val();
				$(this).each(function() {
					setValue(this, data);
				});
				break;
			case 'refresh':
				$(this).each(function() {
					refresh(this);
				});
				break;
			case 'enable':
				$(this).each(function() {
					enable(this);
				});
				break;
			case 'disable':
				$(this).each(function() {
					disable(this);
				});
				break;
			case 'destroy':
				$(this).each(function() {
					destroy(this);
				});
				break;
			default:
				$(this).each(function() {
					init(this, method);
				});
				break;
			}
			return $(this);
		}
	});
})(jQuery);


//


/**
 * jQuery Validation Plugin @VERSION
 *
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * http://docs.jquery.com/Plugins/Validation
 *
 * Copyright (c) 2012 JГ¶rn Zaefferer
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */

(function($) {

$.extend($.fn, {
	// http://docs.jquery.com/Plugins/Validation/validate
	validate: function( options ) {

		// if nothing is selected, return nothing; can't chain anyway
		if (!this.length) {
			if (options && options.debug && window.console) {
				console.warn( "nothing selected, can't validate, returning nothing" );
			}
			return;
		}

		// check if a validator for this form was already created
		var validator = $.data(this[0], 'validator');
		if ( validator ) {
			return validator;
		}

		// Add novalidate tag if HTML5.
		this.attr('novalidate', 'novalidate');

		validator = new $.validator( options, this[0] );
		$.data(this[0], 'validator', validator);

		if ( validator.settings.onsubmit ) {

			this.validateDelegate( ":submit", "click", function(ev) {
				if ( validator.settings.submitHandler ) {
					validator.submitButton = ev.target;
				}
				// allow suppressing validation by adding a cancel class to the submit button
				if ( $(ev.target).hasClass('cancel') ) {
					validator.cancelSubmit = true;
				}
			});

			// validate the form on submit
			this.submit( function( event ) {
				if ( validator.settings.debug ) {
					// prevent form submit to be able to see console output
					event.preventDefault();
				}
				function handle() {
					var hidden;
					if ( validator.settings.submitHandler ) {
						if (validator.submitButton) {
							// insert a hidden input as a replacement for the missing submit button
							hidden = $("<input type='hidden'/>").attr("name", validator.submitButton.name).val(validator.submitButton.value).appendTo(validator.currentForm);
						}
						validator.settings.submitHandler.call( validator, validator.currentForm, event );
						if (validator.submitButton) {
							// and clean up afterwards; thanks to no-block-scope, hidden can be referenced
							hidden.remove();
						}
						return false;
					}
					return true;
				}

				// prevent submit for invalid forms or custom submit handlers
				if ( validator.cancelSubmit ) {
					validator.cancelSubmit = false;
					return handle();
				}
				if ( validator.form() ) {
					if ( validator.pendingRequest ) {
						validator.formSubmitted = true;
						return false;
					}
					return handle();
				} else {
					validator.focusInvalid();
					return false;
				}
			});
		}

		return validator;
	},
	// http://docs.jquery.com/Plugins/Validation/valid
	valid: function() {
		if ( $(this[0]).is('form')) {
			return this.validate().form();
		} else {
			var valid = true;
			var validator = $(this[0].form).validate();
			this.each(function() {
				valid &= validator.element(this);
			});
			return valid;
		}
	},
	// attributes: space seperated list of attributes to retrieve and remove
	removeAttrs: function(attributes) {
		var result = {},
			$element = this;
		$.each(attributes.split(/\s/), function(index, value) {
			result[value] = $element.attr(value);
			$element.removeAttr(value);
		});
		return result;
	},
	// http://docs.jquery.com/Plugins/Validation/rules
	rules: function(command, argument) {
		var element = this[0];

		if (command) {
			var settings = $.data(element.form, 'validator').settings;
			var staticRules = settings.rules;
			var existingRules = $.validator.staticRules(element);
			switch(command) {
			case "add":
				$.extend(existingRules, $.validator.normalizeRule(argument));
				staticRules[element.name] = existingRules;
				if (argument.messages) {
					settings.messages[element.name] = $.extend( settings.messages[element.name], argument.messages );
				}
				break;
			case "remove":
				if (!argument) {
					delete staticRules[element.name];
					return existingRules;
				}
				var filtered = {};
				$.each(argument.split(/\s/), function(index, method) {
					filtered[method] = existingRules[method];
					delete existingRules[method];
				});
				return filtered;
			}
		}

		var data = $.validator.normalizeRules(
		$.extend(
			{},
			$.validator.metadataRules(element),
			$.validator.classRules(element),
			$.validator.attributeRules(element),
			$.validator.staticRules(element)
		), element);

		// make sure required is at front
		if (data.required) {
			var param = data.required;
			delete data.required;
			data = $.extend({required: param}, data);
		}

		return data;
	}
});

// Custom selectors
$.extend($.expr[":"], {
	// http://docs.jquery.com/Plugins/Validation/blank
	blank: function(a) {return !$.trim("" + a.value);},
	// http://docs.jquery.com/Plugins/Validation/filled
	filled: function(a) {return !!$.trim("" + a.value);},
	// http://docs.jquery.com/Plugins/Validation/unchecked
	unchecked: function(a) {return !a.checked;}
});

// constructor for validator
$.validator = function( options, form ) {
	this.settings = $.extend( true, {}, $.validator.defaults, options );
	this.currentForm = form;
	this.init();
};

$.validator.format = function(source, params) {
	if ( arguments.length === 1 ) {
		return function() {
			var args = $.makeArray(arguments);
			args.unshift(source);
			return $.validator.format.apply( this, args );
		};
	}
	if ( arguments.length > 2 && params.constructor !== Array  ) {
		params = $.makeArray(arguments).slice(1);
	}
	if ( params.constructor !== Array ) {
		params = [ params ];
	}
	$.each(params, function(i, n) {
		source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
	});
	return source;
};

$.extend($.validator, {

	defaults: {
		messages: {},
		groups: {},
		rules: {},
		errorClass: "error",
		validClass: "valid",
		errorElement: "label",
		focusInvalid: true,
		errorContainer: $( [] ),
		errorLabelContainer: $( [] ),
		onsubmit: true,
		ignore: ":hidden",
		ignoreTitle: false,
		onfocusin: function(element, event) {
			this.lastActive = element;

			// hide error label and remove error class on focus if enabled
			if ( this.settings.focusCleanup && !this.blockFocusCleanup ) {
				if ( this.settings.unhighlight ) {
					this.settings.unhighlight.call( this, element, this.settings.errorClass, this.settings.validClass );
				}
				this.addWrapper(this.errorsFor(element)).hide();
			}
		},
		onfocusout: function(element, event) {
			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
				this.element(element);
			}
		},
		onkeyup: function(element, event) {
			if ( event.which == 9 && this.elementValue(element) === '' ) {
				return;
			} else if ( element.name in this.submitted || element === this.lastActive ) {
				this.element(element);
			}
		},
		onclick: function(element, event) {
			// click on selects, radiobuttons and checkboxes
			if ( element.name in this.submitted ) {
				this.element(element);
			}
			// or option elements, check parent select in that case
			else if (element.parentNode.name in this.submitted) {
				this.element(element.parentNode);
			}
		},
		highlight: function(element, errorClass, validClass) {
			if (element.type === 'radio') {
				this.findByName(element.name).addClass(errorClass).removeClass(validClass);
			} else {
				$(element).addClass(errorClass).removeClass(validClass);
			}
		},
		unhighlight: function(element, errorClass, validClass) {
			if (element.type === 'radio') {
				this.findByName(element.name).removeClass(errorClass).addClass(validClass);
			} else {
				$(element).removeClass(errorClass).addClass(validClass);
			}
		}
	},

	// http://docs.jquery.com/Plugins/Validation/Validator/setDefaults
	setDefaults: function(settings) {
		$.extend( $.validator.defaults, settings );
	},

	messages: {
		required: "This field is required.",
		remote: "Please fix this field.",
		email: "Please enter a valid email address.",
		url: "Please enter a valid URL.",
		date: "Please enter a valid date.",
		dateISO: "Please enter a valid date (ISO).",
		number: "Please enter a valid number.",
		digits: "Please enter only digits.",
		creditcard: "Please enter a valid credit card number.",
		equalTo: "Please enter the same value again.",
		maxlength: $.validator.format("Please enter no more than {0} characters."),
		minlength: $.validator.format("Please enter at least {0} characters."),
		rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
		range: $.validator.format("Please enter a value between {0} and {1}."),
		max: $.validator.format("Please enter a value less than or equal to {0}."),
		min: $.validator.format("Please enter a value greater than or equal to {0}.")
	},

	autoCreateRanges: false,

	prototype: {

		init: function() {
			this.labelContainer = $(this.settings.errorLabelContainer);
			this.errorContext = this.labelContainer.length && this.labelContainer || $(this.currentForm);
			this.containers = $(this.settings.errorContainer).add( this.settings.errorLabelContainer );
			this.submitted = {};
			this.valueCache = {};
			this.pendingRequest = 0;
			this.pending = {};
			this.invalid = {};
			this.reset();

			var groups = (this.groups = {});
			$.each(this.settings.groups, function(key, value) {
				$.each(value.split(/\s/), function(index, name) {
					groups[name] = key;
				});
			});
			var rules = this.settings.rules;
			$.each(rules, function(key, value) {
				rules[key] = $.validator.normalizeRule(value);
			});

			function delegate(event) {
				var validator = $.data(this[0].form, "validator"),
					eventType = "on" + event.type.replace(/^validate/, "");
				if (validator.settings[eventType]) {
					validator.settings[eventType].call(validator, this[0], event);
				}
			}
			$(this.currentForm)
				.validateDelegate(":text, [type='password'], [type='file'], select, textarea, " +
					"[type='number'], [type='search'] ,[type='tel'], [type='url'], " +
					"[type='email'], [type='datetime'], [type='date'], [type='month'], " +
					"[type='week'], [type='time'], [type='datetime-local'], " +
					"[type='range'], [type='color'] ",
					"focusin focusout keyup", delegate)
				.validateDelegate("[type='radio'], [type='checkbox'], select, option", "click", delegate);

			if (this.settings.invalidHandler) {
				$(this.currentForm).bind("invalid-form.validate", this.settings.invalidHandler);
			}
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/form
		form: function() {
			this.checkForm();
			$.extend(this.submitted, this.errorMap);
			this.invalid = $.extend({}, this.errorMap);
			if (!this.valid()) {
				$(this.currentForm).triggerHandler("invalid-form", [this]);
			}
			this.showErrors();
			return this.valid();
		},

		checkForm: function() {
			this.prepareForm();
			for ( var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++ ) {
				this.check( elements[i] );
			}
			return this.valid();
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/element
		element: function( element ) {
			element = this.validationTargetFor( this.clean( element ) );
			this.lastElement = element;
			this.prepareElement( element );
			this.currentElements = $(element);
			var result = this.check( element ) !== false;
			if (result) {
				delete this.invalid[element.name];
			} else {
				this.invalid[element.name] = true;
			}
			if ( !this.numberOfInvalids() ) {
				// Hide error containers on last error
				this.toHide = this.toHide.add( this.containers );
			}
			this.showErrors();
			return result;
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/showErrors
		showErrors: function(errors) {
			if(errors) {
				// add items to error list and map
				$.extend( this.errorMap, errors );
				this.errorList = [];
				for ( var name in errors ) {
					this.errorList.push({
						message: errors[name],
						element: this.findByName(name)[0]
					});
				}
				// remove items from success list
				this.successList = $.grep( this.successList, function(element) {
					return !(element.name in errors);
				});
			}
			if (this.settings.showErrors) {
				this.settings.showErrors.call( this, this.errorMap, this.errorList );
			} else {
				this.defaultShowErrors();
			}
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/resetForm
		resetForm: function() {
			if ( $.fn.resetForm ) {
				$( this.currentForm ).resetForm();
			}
			this.submitted = {};
			this.lastElement = null;
			this.prepareForm();
			this.hideErrors();
			this.elements().removeClass( this.settings.errorClass ).removeData( "previousValue" );
		},

		numberOfInvalids: function() {
			return this.objectLength(this.invalid);
		},

		objectLength: function( obj ) {
			var count = 0;
			for ( var i in obj ) {
				count++;
			}
			return count;
		},

		hideErrors: function() {
			this.addWrapper( this.toHide ).hide();
		},

		valid: function() {
			return this.size() === 0;
		},

		size: function() {
			return this.errorList.length;
		},

		focusInvalid: function() {
			if( this.settings.focusInvalid ) {
				try {
					$(this.findLastActive() || this.errorList.length && this.errorList[0].element || [])
					.filter(":visible")
					.focus()
					// manually trigger focusin event; without it, focusin handler isn't called, findLastActive won't have anything to find
					.trigger("focusin");
				} catch(e) {
					// ignore IE throwing errors when focusing hidden elements
				}
			}
		},

		findLastActive: function() {
			var lastActive = this.lastActive;
			return lastActive && $.grep(this.errorList, function(n) {
				return n.element.name === lastActive.name;
			}).length === 1 && lastActive;
		},

		elements: function() {
			var validator = this,
				rulesCache = {};

			// select all valid inputs inside the form (no submit or reset buttons)
			return $(this.currentForm)
			.find("input, select, textarea")
			.not(":submit, :reset, :image, [disabled]")
			.not( this.settings.ignore )
			.filter(function() {
				if ( !this.name && validator.settings.debug && window.console ) {
					console.error( "%o has no name assigned", this);
				}

				// select only the first element for each name, and only those with rules specified
				if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
					return false;
				}

				rulesCache[this.name] = true;
				return true;
			});
		},

		clean: function( selector ) {
			return $( selector )[0];
		},

		errors: function() {
			var errorClass = this.settings.errorClass.replace(' ', '.');
			return $( this.settings.errorElement + "." + errorClass, this.errorContext );
		},

		reset: function() {
			this.successList = [];
			this.errorList = [];
			this.errorMap = {};
			this.toShow = $([]);
			this.toHide = $([]);
			this.currentElements = $([]);
		},

		prepareForm: function() {
			this.reset();
			this.toHide = this.errors().add( this.containers );
		},

		prepareElement: function( element ) {
			this.reset();
			this.toHide = this.errorsFor(element);
		},

		elementValue: function( element ) {
			var type = $(element).attr('type'),
				val = $(element).val();

			if ( type === 'radio' || type === 'checkbox' ) {
				return $('input[name="' + $(element).attr('name') + '"]:checked').val();
			}

			if ( typeof val === 'string' ) {
				return val.replace(/\r/g, "");
			}
			return val;
		},

		check: function( element ) {
			element = this.validationTargetFor( this.clean( element ) );

			var rules = $(element).rules();
			var dependencyMismatch = false;
			var val = this.elementValue(element);
			var result;

			for (var method in rules ) {
				var rule = { method: method, parameters: rules[method] };
				try {

					result = $.validator.methods[method].call( this, val, element, rule.parameters );

					// if a method indicates that the field is optional and therefore valid,
					// don't mark it as valid when there are no other rules
					if ( result === "dependency-mismatch" ) {
						dependencyMismatch = true;
						continue;
					}
					dependencyMismatch = false;

					if ( result === "pending" ) {
						this.toHide = this.toHide.not( this.errorsFor(element) );
						return;
					}

					if( !result ) {
						this.formatAndAdd( element, rule );
						return false;
					}
				} catch(e) {
					if ( this.settings.debug && window.console ) {
						console.log("exception occured when checking element " + element.id + ", check the '" + rule.method + "' method", e);
					}
					throw e;
				}
			}
			if (dependencyMismatch) {
				return;
			}
			if ( this.objectLength(rules) ) {
				this.successList.push(element);
			}
			return true;
		},

		// return the custom message for the given element and validation method
		// specified in the element's "messages" metadata
		customMetaMessage: function(element, method) {
			if (!$.metadata) {
				return;
			}
			var meta = this.settings.meta ? $(element).metadata()[this.settings.meta] : $(element).metadata();
			return meta && meta.messages && meta.messages[method];
		},

		// return the custom message for the given element and validation method
		// specified in the element's HTML5 data attribute
		customDataMessage: function(element, method) {
			return $(element).data('msg-' + method.toLowerCase()) || (element.attributes && $(element).attr('data-msg-' + method.toLowerCase()));
		},

		// return the custom message for the given element name and validation method
		customMessage: function( name, method ) {
			var m = this.settings.messages[name];
			return m && (m.constructor === String ? m : m[method]);
		},

		// return the first defined argument, allowing empty strings
		findDefined: function() {
			for(var i = 0; i < arguments.length; i++) {
				if (arguments[i] !== undefined) {
					return arguments[i];
				}
			}
			return undefined;
		},

		defaultMessage: function( element, method) {
			return this.findDefined(
				this.customMessage( element.name, method ),
				this.customDataMessage( element, method ),
				this.customMetaMessage( element, method ),
				// title is never undefined, so handle empty string as undefined
				!this.settings.ignoreTitle && element.title || undefined,
				$.validator.messages[method],
				"<strong>Warning: No message defined for " + element.name + "</strong>"
			);
		},

		formatAndAdd: function( element, rule ) {
			var message = this.defaultMessage( element, rule.method ),
				theregex = /\$?\{(\d+)\}/g;
			if ( typeof message === "function" ) {
				message = message.call(this, rule.parameters, element);
			} else if (theregex.test(message)) {
				message = $.validator.format(message.replace(theregex, '{$1}'), rule.parameters);
			}
			this.errorList.push({
				message: message,
				element: element
			});

			this.errorMap[element.name] = message;
			this.submitted[element.name] = message;
		},

		addWrapper: function(toToggle) {
			if ( this.settings.wrapper ) {
				toToggle = toToggle.add( toToggle.parent( this.settings.wrapper ) );
			}
			return toToggle;
		},

		defaultShowErrors: function() {
			var i, elements;
			for ( i = 0; this.errorList[i]; i++ ) {
				var error = this.errorList[i];
				if ( this.settings.highlight ) {
					this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass );
				}
				this.showLabel( error.element, error.message );
			}
			if( this.errorList.length ) {
				this.toShow = this.toShow.add( this.containers );
			}
			if (this.settings.success) {
				for ( i = 0; this.successList[i]; i++ ) {
					this.showLabel( this.successList[i] );
				}
			}
			if (this.settings.unhighlight) {
				for ( i = 0, elements = this.validElements(); elements[i]; i++ ) {
					this.settings.unhighlight.call( this, elements[i], this.settings.errorClass, this.settings.validClass );
				}
			}
			this.toHide = this.toHide.not( this.toShow );
			this.hideErrors();
			this.addWrapper( this.toShow ).show();
		},

		validElements: function() {
			return this.currentElements.not(this.invalidElements());
		},

		invalidElements: function() {
			return $(this.errorList).map(function() {
				return this.element;
			});
		},

		showLabel: function(element, message) {
			var label = this.errorsFor( element );
			if ( label.length ) {
				// refresh error/success class
				label.removeClass( this.settings.validClass ).addClass( this.settings.errorClass );

				// check if we have a generated label, replace the message then
				if ( label.attr("generated") ) {
					label.html(message);
				}
			} else {
				// create label
				label = $("<" + this.settings.errorElement + "/>")
					.attr({"for":  this.idOrName(element), generated: true})
					.addClass(this.settings.errorClass)
					.html(message || "");
				if ( this.settings.wrapper ) {
					// make sure the element is visible, even in IE
					// actually showing the wrapped element is handled elsewhere
					label = label.hide().show().wrap("<" + this.settings.wrapper + "/>").parent();
				}
				if ( !this.labelContainer.append(label).length ) {
					if ( this.settings.errorPlacement ) {
						this.settings.errorPlacement(label, $(element) );
					} else {
					label.insertAfter(element);
					}
				}
			}
			if ( !message && this.settings.success ) {
				label.text("");
				if ( typeof this.settings.success === "string" ) {
					label.addClass( this.settings.success );
				} else {
					this.settings.success( label, element );
				}
			}
			this.toShow = this.toShow.add(label);
		},

		errorsFor: function(element) {
			var name = this.idOrName(element);
			return this.errors().filter(function() {
				return $(this).attr('for') === name;
			});
		},

		idOrName: function(element) {
			return this.groups[element.name] || (this.checkable(element) ? element.name : element.id || element.name);
		},

		validationTargetFor: function(element) {
			// if radio/checkbox, validate first element in group instead
			if (this.checkable(element)) {
				element = this.findByName( element.name ).not(this.settings.ignore)[0];
			}
			return element;
		},

		checkable: function( element ) {
			return (/radio|checkbox/i).test(element.type);
		},

		findByName: function( name ) {
			return $(this.currentForm).find('[name="' + name + '"]');
		},

		getLength: function(value, element) {
			switch( element.nodeName.toLowerCase() ) {
			case 'select':
				return $("option:selected", element).length;
			case 'input':
				if( this.checkable( element) ) {
					return this.findByName(element.name).filter(':checked').length;
				}
			}
			return value.length;
		},

		depend: function(param, element) {
			return this.dependTypes[typeof param] ? this.dependTypes[typeof param](param, element) : true;
		},

		dependTypes: {
			"boolean": function(param, element) {
				return param;
			},
			"string": function(param, element) {
				return !!$(param, element.form).length;
			},
			"function": function(param, element) {
				return param(element);
			}
		},

		optional: function(element) {
			var val = this.elementValue(element);
			return !$.validator.methods.required.call(this, val, element) && "dependency-mismatch";
		},

		startRequest: function(element) {
			if (!this.pending[element.name]) {
				this.pendingRequest++;
				this.pending[element.name] = true;
			}
		},

		stopRequest: function(element, valid) {
			this.pendingRequest--;
			// sometimes synchronization fails, make sure pendingRequest is never < 0
			if (this.pendingRequest < 0) {
				this.pendingRequest = 0;
			}
			delete this.pending[element.name];
			if ( valid && this.pendingRequest === 0 && this.formSubmitted && this.form() ) {
				$(this.currentForm).submit();
				this.formSubmitted = false;
			} else if (!valid && this.pendingRequest === 0 && this.formSubmitted) {
				$(this.currentForm).triggerHandler("invalid-form", [this]);
				this.formSubmitted = false;
			}
		},

		previousValue: function(element) {
			return $.data(element, "previousValue") || $.data(element, "previousValue", {
				old: null,
				valid: true,
				message: this.defaultMessage( element, "remote" )
			});
		}

	},

	classRuleSettings: {
		required: {required: true},
		email: {email: true},
		url: {url: true},
		date: {date: true},
		dateISO: {dateISO: true},
		number: {number: true},
		digits: {digits: true},
		creditcard: {creditcard: true}
	},

	addClassRules: function(className, rules) {
		if ( className.constructor === String ) {
			this.classRuleSettings[className] = rules;
		} else {
			$.extend(this.classRuleSettings, className);
		}
	},

	classRules: function(element) {
		var rules = {};
		var classes = $(element).attr('class');
		if ( classes ) {
			$.each(classes.split(' '), function() {
				if (this in $.validator.classRuleSettings) {
					$.extend(rules, $.validator.classRuleSettings[this]);
				}
			});
		}
		return rules;
	},

	attributeRules: function(element) {
		var rules = {};
		var $element = $(element);

		for (var method in $.validator.methods) {
			var value;

			// support for <input required> in both html5 and older browsers
			if (method === 'required') {
				value = $element.get(0).getAttribute(method);
				// Some browsers return an empty string for the required attribute
				// and non-HTML5 browsers might have required="" markup
				if (value === "") {
					value = true;
				}
				// force non-HTML5 browsers to return bool
				value = !!value;
			} else {
				value = $element.attr(method);
			}

			if (value) {
				rules[method] = value;
			} else if ($element[0].getAttribute("type") === method) {
				rules[method] = true;
			}
		}

		// maxlength may be returned as -1, 2147483647 (IE) and 524288 (safari) for text inputs
		if (rules.maxlength && /-1|2147483647|524288/.test(rules.maxlength)) {
			delete rules.maxlength;
		}

		return rules;
	},

	metadataRules: function(element) {
		if (!$.metadata) {
			return {};
		}

		var meta = $.data(element.form, 'validator').settings.meta;
		return meta ?
			$(element).metadata()[meta] :
			$(element).metadata();
	},

	staticRules: function(element) {
		var rules = {};
		var validator = $.data(element.form, 'validator');
		if (validator.settings.rules) {
			rules = $.validator.normalizeRule(validator.settings.rules[element.name]) || {};
		}
		return rules;
	},

	normalizeRules: function(rules, element) {
		// handle dependency check
		$.each(rules, function(prop, val) {
			// ignore rule when param is explicitly false, eg. required:false
			if (val === false) {
				delete rules[prop];
				return;
			}
			if (val.param || val.depends) {
				var keepRule = true;
				switch (typeof val.depends) {
					case "string":
						keepRule = !!$(val.depends, element.form).length;
						break;
					case "function":
						keepRule = val.depends.call(element, element);
						break;
				}
				if (keepRule) {
					rules[prop] = val.param !== undefined ? val.param : true;
				} else {
					delete rules[prop];
				}
			}
		});

		// evaluate parameters
		$.each(rules, function(rule, parameter) {
			rules[rule] = $.isFunction(parameter) ? parameter(element) : parameter;
		});

		// clean number parameters
		$.each(['minlength', 'maxlength', 'min', 'max'], function() {
			if (rules[this]) {
				rules[this] = Number(rules[this]);
			}
		});
		$.each(['rangelength', 'range'], function() {
			if (rules[this]) {
				rules[this] = [Number(rules[this][0]), Number(rules[this][1])];
			}
		});

		if ($.validator.autoCreateRanges) {
			// auto-create ranges
			if (rules.min && rules.max) {
				rules.range = [rules.min, rules.max];
				delete rules.min;
				delete rules.max;
			}
			if (rules.minlength && rules.maxlength) {
				rules.rangelength = [rules.minlength, rules.maxlength];
				delete rules.minlength;
				delete rules.maxlength;
			}
		}

		// To support custom messages in metadata ignore rule methods titled "messages"
		if (rules.messages) {
			delete rules.messages;
		}

		return rules;
	},

	// Converts a simple string to a {string: true} rule, e.g., "required" to {required:true}
	normalizeRule: function(data) {
		if( typeof data === "string" ) {
			var transformed = {};
			$.each(data.split(/\s/), function() {
				transformed[this] = true;
			});
			data = transformed;
		}
		return data;
	},

	// http://docs.jquery.com/Plugins/Validation/Validator/addMethod
	addMethod: function(name, method, message) {
		$.validator.methods[name] = method;
		$.validator.messages[name] = message !== undefined ? message : $.validator.messages[name];
		if (method.length < 3) {
			$.validator.addClassRules(name, $.validator.normalizeRule(name));
		}
	},

	methods: {

		// http://docs.jquery.com/Plugins/Validation/Methods/required
		required: function(value, element, param) {
			// check if dependency is met
			if ( !this.depend(param, element) ) {
				return "dependency-mismatch";
			}
			if ( element.nodeName.toLowerCase() === "select" ) {
				// could be an array for select-multiple or a string, both are fine this way
				var val = $(element).val();
				return val && val.length > 0;
			}
			if ( this.checkable(element) ) {
				return this.getLength(value, element) > 0;
			}
			return $.trim(value).length > 0;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/remote
		remote: function(value, element, param) {
			if ( this.optional(element) ) {
				return "dependency-mismatch";
			}

			var previous = this.previousValue(element);
			if (!this.settings.messages[element.name] ) {
				this.settings.messages[element.name] = {};
			}
			previous.originalMessage = this.settings.messages[element.name].remote;
			this.settings.messages[element.name].remote = previous.message;

			param = typeof param === "string" && {url:param} || param;

			if ( this.pending[element.name] ) {
				return "pending";
			}
			if ( previous.old === value ) {
				return previous.valid;
			}

			previous.old = value;
			var validator = this;
			this.startRequest(element);
			var data = {};
			data[element.name] = value;
			$.ajax($.extend(true, {
				url: param,
				mode: "abort",
				port: "validate" + element.name,
				dataType: "json",
				data: data,
				success: function(response) {
					validator.settings.messages[element.name].remote = previous.originalMessage;
					var valid = response === true || response === "true";
					if ( valid ) {
						var submitted = validator.formSubmitted;
						validator.prepareElement(element);
						validator.formSubmitted = submitted;
						validator.successList.push(element);
						delete validator.invalid[element.name];
						validator.showErrors();
					} else {
						var errors = {};
						var message = response || validator.defaultMessage( element, "remote" );
						errors[element.name] = previous.message = $.isFunction(message) ? message(value) : message;
						validator.invalid[element.name] = true;
						validator.showErrors(errors);
					}
					previous.valid = valid;
					validator.stopRequest(element, valid);
				}
			}, param));
			return "pending";
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/minlength
		minlength: function(value, element, param) {
			var length = $.isArray( value ) ? value.length : this.getLength($.trim(value), element);
			return this.optional(element) || length >= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/maxlength
		maxlength: function(value, element, param) {
			var length = $.isArray( value ) ? value.length : this.getLength($.trim(value), element);
			return this.optional(element) || length <= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/rangelength
		rangelength: function(value, element, param) {
			var length = $.isArray( value ) ? value.length : this.getLength($.trim(value), element);
			return this.optional(element) || ( length >= param[0] && length <= param[1] );
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/min
		min: function( value, element, param ) {
			return this.optional(element) || value >= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/max
		max: function( value, element, param ) {
			return this.optional(element) || value <= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/range
		range: function( value, element, param ) {
			return this.optional(element) || ( value >= param[0] && value <= param[1] );
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/email
		email: function(value, element) {
			// contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
			return this.optional(element) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/url
		url: function(value, element) {
			// contributed by Scott Gonzalez: http://projects.scottsplayground.com/iri/
			return this.optional(element) || /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/date
		date: function(value, element) {
			return this.optional(element) || !/Invalid|NaN/.test(new Date(value));
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/dateISO
		dateISO: function(value, element) {
			return this.optional(element) || /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/number
		number: function(value, element) {
			return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/digits
		digits: function(value, element) {
			return this.optional(element) || /^\d+$/.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/creditcard
		// based on http://en.wikipedia.org/wiki/Luhn
		creditcard: function(value, element) {
			if ( this.optional(element) ) {
				return "dependency-mismatch";
			}
			// accept only spaces, digits and dashes
			if (/[^0-9 \-]+/.test(value)) {
				return false;
			}
			var nCheck = 0,
				nDigit = 0,
				bEven = false;

			value = value.replace(/\D/g, "");

			for (var n = value.length - 1; n >= 0; n--) {
				var cDigit = value.charAt(n);
				nDigit = parseInt(cDigit, 10);
				if (bEven) {
					if ((nDigit *= 2) > 9) {
						nDigit -= 9;
					}
				}
				nCheck += nDigit;
				bEven = !bEven;
			}

			return (nCheck % 10) === 0;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/equalTo
		equalTo: function(value, element, param) {
			// bind to the blur event of the target in order to revalidate whenever the target field is updated
			// TODO find a way to bind the event just once, avoiding the unbind-rebind overhead
			var target = $(param);
			if (this.settings.onfocusout) {
				target.unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
					$(element).valid();
				});
			}
			return value === target.val();
		}

	}

});

// deprecated, use $.validator.format instead
$.format = $.validator.format;

}(jQuery));

// ajax mode: abort
// usage: $.ajax({ mode: "abort"[, port: "uniqueport"]});
// if mode:"abort" is used, the previous request on that port (port can be undefined) is aborted via XMLHttpRequest.abort()
(function($) {
	var pendingRequests = {};
	// Use a prefilter if available (1.5+)
	if ( $.ajaxPrefilter ) {
		$.ajaxPrefilter(function(settings, _, xhr) {
			var port = settings.port;
			if (settings.mode === "abort") {
				if ( pendingRequests[port] ) {
					pendingRequests[port].abort();
				}
				pendingRequests[port] = xhr;
			}
		});
	} else {
		// Proxy ajax
		var ajax = $.ajax;
		$.ajax = function(settings) {
			var mode = ( "mode" in settings ? settings : $.ajaxSettings ).mode,
				port = ( "port" in settings ? settings : $.ajaxSettings ).port;
			if (mode === "abort") {
				if ( pendingRequests[port] ) {
					pendingRequests[port].abort();
				}
				return (pendingRequests[port] = ajax.apply(this, arguments));
			}
			return ajax.apply(this, arguments);
		};
	}
}(jQuery));

// provides cross-browser focusin and focusout events
// IE has native support, in other browsers, use event caputuring (neither bubbles)

// provides delegate(type: String, delegate: Selector, handler: Callback) plugin for easier event delegation
// handler is only called when $(event.target).is(delegate), in the scope of the jquery-object for event.target
(function($) {
	// only implement if not provided by jQuery core (since 1.4)
	// TODO verify if jQuery 1.4's implementation is compatible with older jQuery special-event APIs
	if (!jQuery.event.special.focusin && !jQuery.event.special.focusout && document.addEventListener) {
		$.each({
			focus: 'focusin',
			blur: 'focusout'
		}, function( original, fix ){
			$.event.special[fix] = {
				setup:function() {
					this.addEventListener( original, handler, true );
				},
				teardown:function() {
					this.removeEventListener( original, handler, true );
				},
				handler: function(e) {
					var args = arguments;
					args[0] = $.event.fix(e);
					args[0].type = fix;
					return $.event.handle.apply(this, args);
				}
			};
			function handler(e) {
				e = $.event.fix(e);
				e.type = fix;
				return $.event.handle.call(this, e);
			}
		});
	}
	$.extend($.fn, {
		validateDelegate: function(delegate, type, handler) {
			return this.bind(type, function(event) {
				var target = $(event.target);
				if (target.is(delegate)) {
					return handler.apply(target, arguments);
				}
			});
		}
	});
}(jQuery));



/*
 * jQuery outside events - v1.1 - 3/16/2010
 * http://benalman.com/projects/jquery-outside-events-plugin/
 *
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
(function($,c,b){$.map("click dblclick mousemove mousedown mouseup mouseover mouseout change select submit keydown keypress keyup".split(" "),function(d){a(d)});a("focusin","focus"+b);a("focusout","blur"+b);$.addOutsideEvent=a;function a(g,e){e=e||g+b;var d=$(),h=g+"."+e+"-special-event";$.event.special[e]={setup:function(){d=d.add(this);if(d.length===1){$(c).bind(h,f)}},teardown:function(){d=d.not(this);if(d.length===0){$(c).unbind(h)}},add:function(i){var j=i.handler;i.handler=function(l,k){l.target=k;j.apply(this,arguments)}}};function f(i){$(d).each(function(){var j=$(this);if(this!==i.target&&!j.has(i.target).length){j.triggerHandler(e,[i.target])}})}}})(jQuery,document,"outside");


/**
 * Farbtastic Color Picker 1.2
 * © 2008 Steven Wittens
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

jQuery.fn.farbtastic = function (callback) {
  $.farbtastic(this, callback);
  return this;
};

jQuery.farbtastic = function (container, callback) {
  var container = $(container).get(0);
  return container.farbtastic || (container.farbtastic = new jQuery._farbtastic(container, callback));
}

jQuery._farbtastic = function (container, callback) {
  // Store farbtastic object
  var fb = this;

  // Insert markup
  $(container).html('<div class="farbtastic"><div class="color"></div><div class="wheel"></div><div class="overlay"></div><div class="h-marker marker"></div><div class="sl-marker marker"></div></div>');
  var e = $('.farbtastic', container);
  fb.wheel = $('.wheel', container).get(0);
  // Dimensions
  fb.radius = 84;
  fb.square = 100;
  fb.width = 194;

  // Fix background PNGs in IE6
  if (navigator.appVersion.match(/MSIE [0-6]\./)) {
    $('*', e).each(function () {
      if (this.currentStyle.backgroundImage != 'none') {
        var image = this.currentStyle.backgroundImage;
        image = this.currentStyle.backgroundImage.substring(5, image.length - 2);
        $(this).css({
          'backgroundImage': 'none',
          'filter': "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=crop, src='" + image + "')"
        });
      }
    });
  }

  /**
   * Link to the given element(s) or callback.
   */
  fb.linkTo = function (callback) {
    // Unbind previous nodes
    if (typeof fb.callback == 'object') {
      $(fb.callback).unbind('keyup', fb.updateValue);
    }

    // Reset color
    fb.color = null;

    // Bind callback or elements
    if (typeof callback == 'function') {
      fb.callback = callback;
    }
    else if (typeof callback == 'object' || typeof callback == 'string') {
      fb.callback = $(callback);
      fb.callback.bind('keyup', fb.updateValue);
      if (fb.callback.get(0).value) {
        fb.setColor(fb.callback.get(0).value);
      }
    }
    return this;
  }
  fb.updateValue = function (event) {
    if (this.value && this.value != fb.color) {
      fb.setColor(this.value);
    }
  }

  /**
   * Change color with HTML syntax #123456
   */
  fb.setColor = function (color) {
    var unpack = fb.unpack(color);
    if (fb.color != color && unpack) {
      fb.color = color;
      fb.rgb = unpack;
      fb.hsl = fb.RGBToHSL(fb.rgb);
      fb.updateDisplay();
    }
    return this;
  }

  /**
   * Change color with HSL triplet [0..1, 0..1, 0..1]
   */
  fb.setHSL = function (hsl) {
    fb.hsl = hsl;
    fb.rgb = fb.HSLToRGB(hsl);
    fb.color = fb.pack(fb.rgb);
    fb.updateDisplay();
    return this;
  }

  /////////////////////////////////////////////////////

  /**
   * Retrieve the coordinates of the given event relative to the center
   * of the widget.
   */
  fb.widgetCoords = function (event) {
    var x, y;
    var el = event.target || event.srcElement;
    var reference = fb.wheel;

    if (typeof event.offsetX != 'undefined') {
      // Use offset coordinates and find common offsetParent
      var pos = { x: event.offsetX, y: event.offsetY };

      // Send the coordinates upwards through the offsetParent chain.
      var e = el;
      while (e) {
        e.mouseX = pos.x;
        e.mouseY = pos.y;
        pos.x += e.offsetLeft;
        pos.y += e.offsetTop;
        e = e.offsetParent;
      }

      // Look for the coordinates starting from the wheel widget.
      var e = reference;
      var offset = { x: 0, y: 0 }
      while (e) {
        if (typeof e.mouseX != 'undefined') {
          x = e.mouseX - offset.x;
          y = e.mouseY - offset.y;
          break;
        }
        offset.x += e.offsetLeft;
        offset.y += e.offsetTop;
        e = e.offsetParent;
      }

      // Reset stored coordinates
      e = el;
      while (e) {
        e.mouseX = undefined;
        e.mouseY = undefined;
        e = e.offsetParent;
      }
    }
    else {
      // Use absolute coordinates
      var pos = fb.absolutePosition(reference);
      x = (event.pageX || 0*(event.clientX + $('html').get(0).scrollLeft)) - pos.x;
      y = (event.pageY || 0*(event.clientY + $('html').get(0).scrollTop)) - pos.y;
    }
    // Subtract distance to middle
    return { x: x - fb.width / 2, y: y - fb.width / 2 };
  }

  /**
   * Mousedown handler
   */
  fb.mousedown = function (event) {
    // Capture mouse
    if (!document.dragging) {
      $(document).bind('mousemove', fb.mousemove).bind('mouseup', fb.mouseup);
      document.dragging = true;
    }

    // Check which area is being dragged
    var pos = fb.widgetCoords(event);
    fb.circleDrag = Math.max(Math.abs(pos.x), Math.abs(pos.y)) * 2 > fb.square;

    // Process
    fb.mousemove(event);
    return false;
  }

  /**
   * Mousemove handler
   */
  fb.mousemove = function (event) {
    // Get coordinates relative to color picker center
    var pos = fb.widgetCoords(event);

    // Set new HSL parameters
    if (fb.circleDrag) {
      var hue = Math.atan2(pos.x, -pos.y) / 6.28;
      if (hue < 0) hue += 1;
      fb.setHSL([hue, fb.hsl[1], fb.hsl[2]]);
    }
    else {
      var sat = Math.max(0, Math.min(1, -(pos.x / fb.square) + .5));
      var lum = Math.max(0, Math.min(1, -(pos.y / fb.square) + .5));
      fb.setHSL([fb.hsl[0], sat, lum]);
    }
    return false;
  }

  /**
   * Mouseup handler
   */
  fb.mouseup = function () {
    // Uncapture mouse
    $(document).unbind('mousemove', fb.mousemove);
    $(document).unbind('mouseup', fb.mouseup);
    document.dragging = false;
  }

  /**
   * Update the markers and styles
   */
  fb.updateDisplay = function () {
    // Markers
    var angle = fb.hsl[0] * 6.28;
    $('.h-marker', e).css({
      left: Math.round(Math.sin(angle) * fb.radius + fb.width / 2) + 'px',
      top: Math.round(-Math.cos(angle) * fb.radius + fb.width / 2) + 'px'
    });

    $('.sl-marker', e).css({
      left: Math.round(fb.square * (.5 - fb.hsl[1]) + fb.width / 2) + 'px',
      top: Math.round(fb.square * (.5 - fb.hsl[2]) + fb.width / 2) + 'px'
    });

    // Saturation/Luminance gradient
    $('.color', e).css('backgroundColor', fb.pack(fb.HSLToRGB([fb.hsl[0], 1, 0.5])));

    // Linked elements or callback
    if (typeof fb.callback == 'object') {
      // Set background/foreground color
      $(fb.callback).css({
        backgroundColor: fb.color,
        color: fb.hsl[2] > 0.5 ? '#000' : '#fff'
      });

      // Change linked value
      $(fb.callback).each(function() {
        if (this.value && this.value != fb.color) {
          this.value = fb.color;
        }
      });
    }
    else if (typeof fb.callback == 'function') {
      fb.callback.call(fb, fb.color);
    }
  }

  /**
   * Get absolute position of element
   */
  fb.absolutePosition = function (el) {
    var r = { x: el.offsetLeft, y: el.offsetTop };
    // Resolve relative to offsetParent
    if (el.offsetParent) {
      var tmp = fb.absolutePosition(el.offsetParent);
      r.x += tmp.x;
      r.y += tmp.y;
    }
    return r;
  };

  /* Various color utility functions */
  fb.pack = function (rgb) {
    var r = Math.round(rgb[0] * 255);
    var g = Math.round(rgb[1] * 255);
    var b = Math.round(rgb[2] * 255);
    return '#' + (r < 16 ? '0' : '') + r.toString(16) +
           (g < 16 ? '0' : '') + g.toString(16) +
           (b < 16 ? '0' : '') + b.toString(16);
  }

  fb.unpack = function (color) {
    if (color.length == 7) {
      return [parseInt('0x' + color.substring(1, 3)) / 255,
        parseInt('0x' + color.substring(3, 5)) / 255,
        parseInt('0x' + color.substring(5, 7)) / 255];
    }
    else if (color.length == 4) {
      return [parseInt('0x' + color.substring(1, 2)) / 15,
        parseInt('0x' + color.substring(2, 3)) / 15,
        parseInt('0x' + color.substring(3, 4)) / 15];
    }
  }

  fb.HSLToRGB = function (hsl) {
    var m1, m2, r, g, b;
    var h = hsl[0], s = hsl[1], l = hsl[2];
    m2 = (l <= 0.5) ? l * (s + 1) : l + s - l*s;
    m1 = l * 2 - m2;
    return [this.hueToRGB(m1, m2, h+0.33333),
        this.hueToRGB(m1, m2, h),
        this.hueToRGB(m1, m2, h-0.33333)];
  }

  fb.hueToRGB = function (m1, m2, h) {
    h = (h < 0) ? h + 1 : ((h > 1) ? h - 1 : h);
    if (h * 6 < 1) return m1 + (m2 - m1) * h * 6;
    if (h * 2 < 1) return m2;
    if (h * 3 < 2) return m1 + (m2 - m1) * (0.66666 - h) * 6;
    return m1;
  }

  fb.RGBToHSL = function (rgb) {
    var min, max, delta, h, s, l;
    var r = rgb[0], g = rgb[1], b = rgb[2];
    min = Math.min(r, Math.min(g, b));
    max = Math.max(r, Math.max(g, b));
    delta = max - min;
    l = (min + max) / 2;
    s = 0;
    if (l > 0 && l < 1) {
      s = delta / (l < 0.5 ? (2 * l) : (2 - 2 * l));
    }
    h = 0;
    if (delta > 0) {
      if (max == r && max != g) h += (g - b) / delta;
      if (max == g && max != b) h += (2 + (b - r) / delta);
      if (max == b && max != r) h += (4 + (r - g) / delta);
      h /= 6;
    }
    return [h, s, l];
  }

  // Install mousedown handler (the others are set on the document on-demand)
  $('*', e).mousedown(fb.mousedown);

    // Init color
  fb.setColor('#000000');

  // Set linked elements/callback
  if (callback) {
    fb.linkTo(callback);
  }
}