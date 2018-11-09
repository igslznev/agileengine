<h2>Smart XML Analyzer</h2>

Analyzer take two html files and in first file taking element with <code>id=make-everything-ok-button</code> as original. 

To run analyzer execute following line:

>java -cp agileengine.jar <input_origin_file_path> <input_other_sample_file_path>

And in the output you will see XML path to the target element or message about no target file found.

As optional value you can provide <code>id</code> of your element as 3rd parameter:

>java -cp agileengine.jar <input_origin_file_path> <input_other_sample_file_path> <your_element_id>

Further improvement might include combined selector with classes and attributes, with attributes values (e.g.<code>[rel=next]</code>), different combination of attributes and classes.


