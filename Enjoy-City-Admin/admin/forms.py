from django import forms

from .models import Location, Tag, Content


class LocationForm(forms.ModelForm):
    tags_field = forms.MultipleChoiceField(label='Tags', required=False)

    def __init__(self, *args, **kwargs):
        self.user = kwargs.pop('user', None)

        super(LocationForm, self).__init__(*args, **kwargs)

         #Add options
        tags = [(idx, tag.value) for idx, tag in enumerate(Tag.objects.all())]
        self.fields['tags_field'].choices = tags

         #If the object exists, load the initial values
        if self.instance.pk:
            self.fields['tags_field'].initial = self.instance.tags


    def save(self, *args, **kwargs):
        self.instance.tags = self.cleaned_data['tags_field']
        self.instance.user = self.user
        return super(LocationForm, self).save()

    class Meta:
        model = Location
        exclude = ('tags', 'user', 'rating')

class ContentForm(forms.ModelForm):
    class Meta:
        model = Content
        exclude = ('tags', )
