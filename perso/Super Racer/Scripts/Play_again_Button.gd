extends Button

func _ready():
	pass


func _on_Button_pressed():
	get_parent().visible = false
	get_tree().change_scene("res://Space.tscn")
