/**
 * 视图渲染
 */

/**
 * 弹出提示
 * 
 * @param msg 提示消息
 * @param level 提示等级
 * @param durability 提示持续时间（默认2秒）
 * @param speed 提示出现/消失速度（默认0.5秒）
 * @returns
 */
function tip(msg, level = 'info', durability = 2000, speed = 500) {
	$('.tip-fixed').remove();  // 移除已有提示
	var tip_jq = $('<div class="tip-fixed">' + msg + '</div>')
	switch (level) {
		case 'info':
			tip_jq.addClass('color-info');
			break;
		case 'warning':
			tip_jq.addClass('color-warning');
			break;
		case 'error':
			tip_jq.addClass('color-error');
			break;
		case 'success':
			tip_jq.addClass('color-success');
			break;
		case 'disabled':
			tip_jq.addClass('color-disabled');
			break;
		default:
			tip_jq.addClass('color-gray');
			break;
	}
	tip_jq.hide().appendTo('body').fadeIn(speed);
	window.setTimeout(function() {
		tip_jq.fadeOut(speed, function() {
			tip_jq.remove();
		});
	}, durability);
}

/**
 * 数据视图渲染
 * <p/>
 * <p>层级关系：container（容器） > unit（遍历单元） > elem（元素/项）</p>
 *
 * @param dataset 数据集
 * @param container 视图容器
 * @returns
 */
function dv_rendering(dataset, container, map_resolve = $.noop, unit_resolve = $.noop) {
	var rendering_result = [];
	if (container == null) {
		return;
	}
	var unit_model = container.children('.dv-unit:hidden').show();  // 获取遍历单元
	for (let i in dataset) {
		let data = dataset[i];
		let unit = unit_model.clone(true);  // 复制遍历单元模板
		
		// 处理 index 型元素（序号）
		unit.find('.dv-elem-index').text(parseInt(i)+1);
		
		// 处理 expose 型元素（值）
		unit.find('.dv-elem-expose').each(function() {
			$(this).text(data[$(this).attr('dv-elem')]);
		});
		
		// 处理 map 型元素（映射）
		unit.find('.dv-elem-map').each(function() {
			map_resolve(data, $(this));
		});
		
		// 处理 fixed 型数据（固定）
		// 你想处理啥？
		
		// 处理遍历单元本身
		unit.attr('id', data['id']);
		unit_resolve(data);  // 额外业务则自行指定
		
		// 处理完毕，向渲染列表追加jquery对象
		rendering_result.push(unit);
		
	}
	
	// 遍历完毕，向容器追加jquery对象，正式渲染
	container.empty();  // 清空容器内原内容
	$.each(rendering_result, function(i, val) {
		container.append(val);
	});
	unit_model.hide();
	container.append(unit_model);
}