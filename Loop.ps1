for ($i=0; $i -lt 4; $i++)
{
	rm -recurse ./packageOut/existingAppx
	& "C:\Program Files (x86)\Windows Kits\10\bin\10.0.19041.0\x64\makeappx.exe" unpack /p "./Appx2.appx" /d "./packageOut/existingAppx"

	#rm ./packageOut/repack.appx
	cp ./Appx2.appx ./packageOut/existingAppx
	& "C:\Program Files (x86)\Windows Kits\10\bin\10.0.19041.0\x64\makeappx.exe" pack /d "./packageOut/existingAppx" /p "./packageOut/repack"
}



