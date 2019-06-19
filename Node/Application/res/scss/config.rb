# Ist die Config für den SCSS zu CSS Compiler: Compass.
# Sorgt dafür, dass die *.scss in diesem 
# Ordner in den css-Ordner als *.css compiliert werden.


dir = File.dirname(__FILE__)
sass_path = dir
css_path = File.join(dir, "..", "css")
environment  = :development
#output_style = :expanded
output_style = :compressed
