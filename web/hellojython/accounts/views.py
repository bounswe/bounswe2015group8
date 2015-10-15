from django.shortcuts import render, render_to_response, redirect
from accounts.models import User
from django.http import HttpResponseRedirect
from django.template import RequestContext
from django.core.urlresolvers import reverse

def get_home(request):
	hello = "Welcome to our application!"
	users = User.objects.all()
	return render(request, 'accounts/default.html', {'welcome': hello, 'users': users})

def add_user(request):
	username = request.POST.get('username')
	password = request.POST.get('password')
	user = User(username=username, password=password)
	user.save()
	return HttpResponseRedirect(reverse('get_home'))

# Create your views here.
