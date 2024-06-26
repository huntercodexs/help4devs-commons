
# LINUX TIPS

###### USER CREATE

<pre>
sudo /usr/sbin/adduser --user-group --no-create-home --shell=/sbin/nologin --comment='{USERNAME} account' {USERNAME}
</pre>

###### USER DELETE

<pre>
sudo /usr/sbin/userdel {USERNAME}
</pre>

###### CHECK USER

<pre>
cat /etc/passwd
</pre>

##### CHECK GROUPS

<pre>
cat /etc/group
</pre>

###### SSH CONNECT USING PEM

<pre>
sudo ssh -i /home/ec2-user/key/KEY-NAME-AWS.pem ec2-user@10.0.7.160
sudo ssh -i /home/ec2-user/key/KEY-NAME-AWS.pem ubuntu@10.0.X.X
</pre>

###### CHECK UBUNTU RELEASE INFORMATION

<pre>
cat /etc/lsb-release
</pre>

<pre>
DISTRIB_ID=Ubuntu
DISTRIB_RELEASE=20.04
DISTRIB_CODENAME=focal
DISTRIB_DESCRIPTION="Ubuntu 20.04.6 LTS"
</pre>


###### CHECK AVAILABLE PORTS

<pre>
sudo netstat
sudo netstat -ano
sudo netstat -tunl
sudo netstat -tulpn
sudo netstat -ano | grep 443
sudo netstat -ano | egrep '443|I-Node'
sudo netstat -na | grep tcp | grep -i listen
</pre>



<h2>Procedimentos Gerais</h2>

<h3>Recuperar kernel após atualização do sitema</h3>

<p>
	Quando uma atualização é feita e atualizada o kernel e seus headers files, pode ser que ocorra
	problemas inesperados durante o processo de boot, ou até mesmo após o processo de boot durante a
	inicialização do sistema, como por exemplo um problema de video. Para corrigir, ou voltar ao estado
	anterior estavel (que funcionava) podemos seguir o seguinte passo a passo:
</p>

<p>
	<strong>
		NOTA: Antes de qualquer ação faça um backup dos arquivos
	</strong>
</p>

<p>
	<strong>

		NOTA: arquivos de kernel carregados durante o processo de boot ficam no diretorio /boot
	</strong>
</p>

<p>
	<strong>
		NOTA: preciso saber qual o kernel que funcionava para usar nesse processo
	</strong>
</p>

<h4>Verificar o arquivo /etc/default/grub</h4>

<p>
	Deve se ter cuidao em alterar esse arquivo, apenas altere a linha:
</p>

<pre>
	de GRUB_DEFAULT=0 para GRUB_DEFAULT=saved
</pre>

<p>
	e adicione a linha
</p>

<pre>
	GRUB_SAVEDEFAULT=true
</pre>

<h4>Verificar o arquivo /boot/grub/grub.cfg</h4>

<p>
	Nesse arquivo é preciso identificar a linha de comando de Menu export linux_gfx_mode que tem em
	sequencia o kernel que sera carregado durante o processo. Após descobrir qual é o kernel correto
	a ser carregado, basta alterar esse bloco de codigo inserindo o kernel desejado colocando o kernel
	problematico em segundo ou terceiro plano, conforme exemplo abaixo:
</p>

<pre>
	export linux_gfx_mode
	menuentry 'Ubuntu' --class ubuntu --class gnu-linux --class gnu --class os $menuentry_id_option 'gnulinux-simple-f569871c-76ce-4e25-842c-ab8039ead5f4' {
		recordfail
		load_video
		gfxmode $linux_gfx_mode
		insmod gzio
		if [ x$grub_platform = xxen ]; then insmod xzio; insmod lzopio; fi
		insmod part_msdos
		insmod ext2
		if [ x$feature_platform_search_hint = xy ]; then
		  search --no-floppy --fs-uuid --set=root  f569871c-76ce-4e25-842c-ab8039ead5f4
		else
		  search --no-floppy --fs-uuid --set=root f569871c-76ce-4e25-842c-ab8039ead5f4
		fi
		[[[[ALTERAR A LINHA ABAIXO COM O KERNEL CORRETO]]]]
		linux	/boot/vmlinuz-5.8.0-59-generic root=UUID=f569871c-76ce-4e25-842c-ab8039ead5f4 ro  quiet splash $vt_handoff
		linux	/boot/vmlinuz-5.8.0-42-generic root=UUID=f569871c-76ce-4e25-842c-ab8039ead5f4 ro  quiet splash $vt_handoff << EXEMPLO
		[[[[ALTERAR A LINHA ABAIXO COM O KERNEL CORRETO]]]]
		initrd	/boot/initrd.img-5.8.0-59-generic
		initrd	/boot/initrd.img-5.8.0-42-generic << EXEMPLO
	}
</pre>

<h4>Atualizar o grub e reiniciar a maquina</h4>

<pre>
	sudo update-grub
	reboot

	#Antes de reiniciar a maquina, verificar se o grub foi esta corretamente atualizado de acordo com o kernel desejado.
</pre>

<hr />

<h2>Instalar drivers NVIDIA</h2>

<p>
	Para instalar os drivers nvidia corretamente em uma maquina que utilize placas Nvidia, siga as instruções a abaixo:
</p>

<h3>Para saber se o driver já esta instalado digite:</h3>

<pre>
	lsmod | grep nvidia
</pre>

<p>
	O comando não deve retornar nenhum resultado
</p>

<h3>Verificar se o driver generico nouveau esta em memoria:</h3>

<pre>
	lsmod | grep nouveau
</pre>

<p>
	O comando devera retornar os drivers nouveau carregados na memoria
</p>

<h3>Descobrir qual o driver a ser utilizado:</h3>

<p>
	Para isso visite o site NVIDIA DRIVERS e busque pelo modelo exato da placa de video, sistema operacional,
	ramo, e idioma. Após saber qual a versão correta do driver a ser utilizado digite o seguinte comando
	no teminal:
</p>

<pre>
	apt search nvidia
</pre>

<p>
	O comando devera retornar uma lista de drivers, atualizações e features disponiveis para Nvidia Graphics,
	vamos supor que a versão do driver em questão é nvidia-390, então para instalar basta executar o comando:
</p>

<pre>
	apt install nvidia-390
</pre>

<p>
	Após a instalação, não reinicie a maquina, vá até o diretório /etc/modprobe.d e verifique se algum arquivo
	nvidia.conf foi criado com o nome nvidia.conf ou similar, verifique seu conteudo e constate se o
	conteudo é o mesmo do abaixo:
</p>

<pre>
	blacklist nouveau
	blacklist lbm-nouveau
	alias nouveau off
	alias lbm-nouveau off
</pre>

<p>
	Caso o conteudo seja diferente ou o arquivo não tenha sido criado, crie o arquivo e insira esse conteudo no mesmo.
	Ainda no dirotorio /etc/modprobe.d execute o comando:
</p>

<pre>
	grep nvidia *
</pre>

<p>
	e verifque se existe algum recurso nvidia em algum dos arquivos, como no caso do arquivo blacklist-framebuffer.conf
	que contem a linha blacklist nvidiafb, basta comentar a linha como mostrado abaixo
</p>

<pre>
	#blacklist nvidiafb
</pre>

<h4>Recarregar o INIT RAM FS para todos os kernels</h4>

<p>
	Execute o comando abaixo para recompilar e atualizar os kernels com o novo driver instalado:
</p>

<pre>
	sudo update-initramfs -u
</pre>

<p>
	Reinicie a maquina
</p>

<pre>
	reboot
</pre>

<h3>Verificar se o driver foi instalado e carregado corretamente</h3>

<pre>
	lsmod | grep nvidia
</pre>

<p>
	O comando deve retornar os seguintes valores, ou similares:
</p>

<pre>
	nvidia_uvm             36864  0
	nvidia              10592256  88 nvidia_uvm
	nvidiafb               53248  0
	vgastate               20480  1 nvidiafb
	fb_ddc                 16384  1 nvidiafb
	i2c_algo_bit           16384  1 nvidiafb
	drm                   552960  6 nvidia
</pre>

<hr />

<h2>Configurar Inicialização de Programas no boot do Linux (Ubuntu)</h2>

<p>
	Para configurar um programa ou um script em bash para "subir" junto com a inicialização
	de boot do sistema siga as instruções abaixo:
</p>

<p>
	<strong>
		NOTA: Para efeitos demonstrativos será mostrado como subir no boot o anydesk, um programa para acessar
	remotamente computadores na rede local ou na internet.
	</strong>
</p>

<h3>Programa de acesso remoto Anydesk</h3>

<p>
	Após instalar e configurar corretamente o programa Anydesk, crie um script bash no diretório
	/etc/init.d com o seguinte comando:
</p>

<pre>
	sudo touch anydesk
</pre>

<p>
	e adicione o conteudo a seguir
</p>

<pre class="code-style">
	#!/bin/bash

	/usr/bin/anydesk --tray &
	/usr/bin/anydesk &
</pre>

<h3>Link simbolico</h3>

<p>
	- Crie um link no diretório /etc/rc2.d apontando para o script criado em /etc/init.d
</p>

<pre>
	sudo ln -s /etc/init.d/anydesk /etc/rc2.d/S99anydesk
</pre>

<p>
	- Repare que a composição do nome do link contem S99 + nome do script alvo: S99anydesk
	<br />
	- Reinicie a maquina com o comando reboot
	<br />
	- No caso em questão estando sendo testado o AnyDesk, então ao ser carregado o sistema durante o
	processo de boot, será possível acessar a maquina mesmo que não tenha sido efetuado qualquer login.
</p>
